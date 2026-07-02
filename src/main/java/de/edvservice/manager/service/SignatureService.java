package de.edvservice.manager.service;

import java.time.LocalDateTime;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import de.edvservice.manager.entity.order.Order;
import de.edvservice.manager.entity.order.OrderStatus;
import de.edvservice.manager.entity.report.Report;
import de.edvservice.manager.entity.report.ReportStatus;
import de.edvservice.manager.exception.SignatureModificationException;

@Service
public class SignatureService {

	public void applyToOrder(Order order, String customerSignatureData, String employeeSignatureData) {
		boolean locked = order.getStatus() != OrderStatus.DRAFT;

		applySignatureField(
				locked,
				order.getCustomerSignatureData(),
				customerSignatureData,
				order::setCustomerSignatureData,
				order::setCustomerSignedAt);
		applySignatureField(
				locked,
				order.getEmployeeSignatureData(),
				employeeSignatureData,
				order::setEmployeeSignatureData,
				order::setEmployeeSignedAt);

		if (hasSignature(order.getCustomerSignatureData()) && hasSignature(order.getEmployeeSignatureData())) {
			order.setStatus(OrderStatus.CONFIRMED);
		}
	}

	public void applyToReport(Report report, String customerSignatureData, String employeeSignatureData) {
		boolean locked = report.getStatus() != ReportStatus.DRAFT;

		applySignatureField(
				locked,
				report.getCustomerSignatureData(),
				customerSignatureData,
				report::setCustomerSignatureData,
				report::setCustomerSignedAt);
		applySignatureField(
				locked,
				report.getEmployeeSignatureData(),
				employeeSignatureData,
				report::setEmployeeSignatureData,
				report::setEmployeeSignedAt);

		if (hasSignature(report.getCustomerSignatureData()) && hasSignature(report.getEmployeeSignatureData())) {
			report.setStatus(ReportStatus.SIGNED);
		}
	}

	private void applySignatureField(
			boolean locked,
			String existing,
			String incoming,
			Consumer<String> dataSetter,
			Consumer<LocalDateTime> signedAtSetter) {
		if (locked) {
			if (signatureChanged(existing, incoming)) {
				throw new SignatureModificationException();
			}
			return;
		}

		if (!hasSignature(incoming)) {
			dataSetter.accept(null);
			signedAtSetter.accept(null);
			return;
		}

		if (!incoming.equals(existing)) {
			dataSetter.accept(incoming);
			signedAtSetter.accept(LocalDateTime.now());
		}
	}

	private boolean signatureChanged(String existing, String incoming) {
		if (!hasSignature(incoming) && !hasSignature(existing)) {
			return false;
		}
		if (!hasSignature(incoming) || !hasSignature(existing)) {
			return true;
		}
		return !incoming.equals(existing);
	}

	static boolean hasSignature(String data) {
		return data != null && !data.isBlank();
	}
}
