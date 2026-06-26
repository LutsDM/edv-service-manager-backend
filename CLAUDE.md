# CLAUDE.md — EDV Service Manager

## Project Overview

Internal EDV service management system for creating:

- Auftragsformular (work order form)
- Servicebericht (service report)

The application is designed to automate work tracking, reporting, PDF generation, and service documentation for a computer repair and IT service company.

Deployment:
https://lutsdm.github.io/rechner/

---

# Frontend Architecture

- Next.js (App Router)
- TypeScript
- Tailwind CSS
- React Hooks based architecture
- PDF generation handled inside `report/`
- Mobile-first UI design

## Frontend Rules

- Business logic must live inside `hooks/`
- UI components should remain presentation-only
- Prefer reusable blocks and modular structure
- Tailwind CSS preferred over custom CSS
- Avoid unnecessary re-renders
- Use dynamic imports for heavy PDF modules

---

# Backend Architecture

- Spring Boot REST API (Java 21)
- PostgreSQL as primary database
- Schema managed by Hibernate (`spring.jpa.hibernate.ddl-auto: update`)
- No Flyway — schema is generated from JPA entities
- Layered architecture:

```text
Controller -> Service -> Repository
```

- DTO pattern mandatory: `*RequestDto` for input, `*ResponseDto` for output
- No direct entity exposure
- MapStruct for entity ↔ DTO mapping (`toResponseDto`)
- Domain entities: `Order` (Auftragsformular), `Report` (Servicebericht) — short names inside packages `order` / `report`
- REST API: `/api/orders`, `/api/reports`
- JWT authentication planned

## Database Rules (Backend)

- Greenfield project: entity-driven schema via Hibernate `ddl-auto: update`
- Do not add Flyway or SQL migration files unless explicitly requested
- Configuration: `src/main/resources/application.yaml`
- Tests use profile `test` with in-memory H2 (`application-test.yaml`)
- Default database: `edv_service_manager` on `localhost:5432`
- Override credentials via `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`

## JPA Entity Conventions (Backend)

- IDs: `GenerationType.IDENTITY` on all entities (PostgreSQL `BIGSERIAL`)
- Long text fields (signatures, descriptions): `@Column(columnDefinition = "TEXT")` — not `@Lob` (avoids PostgreSQL OID issues)
- Timestamps: Hibernate `@CreationTimestamp` / `@UpdateTimestamp`
- Relations: `FetchType.LAZY` on `@ManyToOne` / `@OneToOne`
- Snake_case column names via explicit `@Column(name = "...")`

## Signatures (Backend)

- Customer and employee signatures on `Order` and `Report`
- Frontend: HTML Canvas → PNG data URL (`data:image/png;base64,...`)
- Backend stores the full data URL as PostgreSQL `TEXT` (`columnDefinition = "TEXT"`)
- Entity/DTO fields: `customerSignatureData`, `employeeSignatureData`
- Timestamps: `customerSignedAt`, `employeeSignedAt` (set by server, not client)
- PDF decoding happens on the frontend; backend only persists and returns the string

## Backend Commands

```bash
./mvnw spring-boot:run   # local API server (port 8080)
./mvnw compile             # compile
./mvnw test                # run tests
```

---

# Current Development Goals

Current project priorities:

1. Introduce PostgreSQL persistence
2. Replace localStorage drafts with backend storage
3. Implement Customer CRUD
4. Implement Auftragsformular CRUD (`Order`)
5. Implement Servicebericht CRUD (`Report`)
6. Generate PDFs from database entities
7. Add authentication system
8. Build admin/report history system

---

# Service Workflow

The application supports two independent workflow scenarios.

## 1. Workflow with a digital service order

1. Create a service order draft.
2. Enter and validate the service order data.
3. Collect the customer and employee signatures.
4. Save the signed service order.
5. Create a service report draft based on the saved service order.
6. Enter the performed work and report details.
7. Collect the customer and employee signatures.
8. Save the signed service report.

```text
Order Draft → Sign Order → Save Order → Report Draft → Sign Report → Save Report
```

## 2. Workflow without a digital service order

A report can be created independently when the original order was received on paper or through another external process.

1. Create a standalone report draft.
2. Enter the customer, service, and performed work details.
3. Optionally enter the external or paper order number (`externalOrderNumber`).
4. Collect the customer and employee signatures.
5. Save the signed service report.

```text
Report Draft → Sign Report → Save Report
```

## Data model rule

The `order` relationship on `Report` is **optional**.

A `Report` may:
- reference an existing digital `Order` (`order_id`)
- reference an external/paper order number (`external_order_number`)
- exist without any order reference

**Never require a digital `Order` to exist before creating a `Report`.**

---

# AI Restrictions

- Do not rewrite existing architecture
- Do not introduce new libraries without reason
- Do not move files automatically
- Preserve current naming conventions (`*RequestDto`, `*ResponseDto`, `*SignatureData`)
- Preserve German business terminology
- Prefer extending existing hooks over creating duplicates
- Avoid unnecessary abstractions
- Minimize file changes
- Preserve PDF generation behavior unless explicitly requested
- Do not add Flyway unless explicitly requested or approaching production

---

# Commands

```bash
npm run dev       # local development server
npm run build     # production build
npm run lint      # ESLint validation
```

---

# Project Structure

```text
app/
  components/
    time/
      blocks/           # Large UI sections (one block = one form section)
        ActionsBlock        # Action buttons
        ArbeitszeitBlock    # Working time (Von / Bis)
        CustomerModal       # Customer information
        EmployeesBlock      # Employee selection
        HeaderBlock         # Header section
        LineItemsModal      # Additional order positions
        OrderDetailsBlock   # Order details
        OrderDetailsModal   # Order details modal
        PasswordModal       # Password protection
        ReportSummaryBlock  # Final report summary
        TravelTimeBlock     # Travel time (Abfahrt / Ankunft)

      hooks/            # All business logic lives here
        useEmployeesSelection
        useOrderFormPdfDownload
        usePdfDownload
        usePriceCalculation
        useTimeCalculation
        useTimeRange

      lib/              # Utility helpers

      report/           # PDF generation and reports
        DocumentPreview
        orderFormAgbContent
        OrderFormPdf
        OrderFormReport
        ServiceReport
        ServiceReportPdf

      Signature/        # Signature system
        SignatureModal
        SignaturePad

      ui/               # Small reusable UI components
        ReportRow
        TimeBlock
        TimeRow

      TimeCalculator.tsx  # Root component

  types/                # Centralized TypeScript types

  globals.css
  layout.tsx
  page.tsx
```

---

# Architecture Rules

- All business logic must remain inside `hooks/`
- Components should only handle rendering and hook usage
- `blocks/` represent isolated form sections
- `ui/` contains reusable presentational components only
- `report/` should not be modified without explicit PDF/report tasks
- All TypeScript interfaces and types must remain centralized in `types/`

---

# Core Business Logic (Do Not Break)

## Price Calculation (`usePriceCalculation`)

- Single hourly rate for both work time and travel time
- Minute-precise calculation without intermediate rounding
- Formula:

```text
(work minutes + travel minutes) × (hourly rate / 60)
```

- Only the final result may be rounded to 2 decimal places

---

## Time Validation (`useTimeCalculation`, `useTimeRange`)

Validation rules:

- Work start < Work end
- Departure ≤ Arrival
- Work start must not be earlier than Arrival
- Departure must not be later than Work end
- Travel time is optional and validated only if fields are filled

---

## UI Rules

- Seconds must never be displayed
- Single date per working day
- Mobile-first layout
- Preserve existing German UI terminology

---

# Code Requirements

- Strict TypeScript only
- No `any`
- All shared types belong in `types/`
- Avoid side effects between blocks
- Keep changes minimal and isolated
- Preserve existing architecture whenever possible
- Prefer extending existing functionality over rewriting

---

# Common Mistakes

- Never round intermediate minute calculations
- Travel validation must only run if travel fields exist
- Do not place business logic inside components
- After changing TypeScript types, verify all usages
- Avoid duplicating hooks with overlapping responsibilities
- Do not use `@Lob` for String fields on PostgreSQL — use `@Column(columnDefinition = "TEXT")`
- Do not name signature fields `*Svg` — format is PNG data URL, use `*SignatureData`
- Hibernate does not rename DB columns; change `@Column(name)` or recreate schema in dev
- Do not rely on `ddl-auto: update` in production — switch to `validate` + schema tooling later
- Do not make `Report.order` required — the order reference is optional by design (standalone report workflow exists)