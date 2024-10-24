package com.hiberus.university;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {

		// 1. Generar el documento PDF con un fichero sin compilar (.jrxml)
		generatePdfFromJrxml();

		// 2. Generar el documento PDF con un fichero compilado (.jasper)
		generatePdfFromJasper();
	}

	private static void generatePdfFromJrxml() {
		try {
			logger.info("Main.generatePdfFromJrxml() - Init");

			// 1. Compilar el archivo .jrxml a un archivo .jasper
			String jrxmlFile = "src/main/resources/templates/report_template.jrxml";
			JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFile);

			// 2. Proporcionar parametros si es necesario (en este ejemplo no hay)
			Map<String, Object> parameters = new HashMap<>();

			// 3. Llenar el reporte (por ejemplo, se puede conectar a una base de datos)
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
					new net.sf.jasperreports.engine.JREmptyDataSource());

			// 4. Exportar el reporte a PDF
			String pdfFile = "src/main/resources/output/report1.pdf";
			JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFile);

			logger.debug("Report successfully generated at: " + pdfFile);

		} catch (JRException e) {
			logger.error("Main.generatePdfFromJrxml() - JRException", e.getMessage());
			logger.error("Exception message:", e);
		} finally {
			logger.info("Main.generatePdfFromJrxml() - End");
		}
	}

	private static void generatePdfFromJasper() {
		try {
			logger.info("Main.generatePdfFromJasper() - Init");

			// 1. Cargar el archivo .jasper ya compilado
			String jasperFile = "src/main/resources/templates/report_template.jasper";
			JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(jasperFile);

			// 2. Proporcionar parametros si es necesario (en este ejemplo no hay)
			Map<String, Object> parameters = new HashMap<>();

			// 3. Llenar el reporte (por ejemplo, se puede conectar a una base de datos)
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
					new net.sf.jasperreports.engine.JREmptyDataSource());

			// 4. Exportar el reporte a PDF
			String pdfFile = "src/main/resources/output/report2.pdf";
			JasperExportManager.exportReportToPdfFile(jasperPrint, pdfFile);

			logger.debug("Report successfully generated at: " + pdfFile);

		} catch (JRException e) {
			logger.error("Main.generatePdfFromJasper() - JRException", e.getMessage());
			logger.error("Exception message:", e);
		} finally {
			logger.info("Main.generatePdfFromJasper() - End");
		}
	}
}
