package com.hiberus.university;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {

		// 1. Generar el documento PDF con un fichero compilado (.jasper)
		generatePdfFromJasper();

	}

	private static void generatePdfFromJasper() {
		try {
			logger.info("Main.generatePdfFromJasper() - Init");

			// 1. Cargar los archivos .jasper ya compilados
			String jasperFileOne = "src/main/resources/templates/01report_template.jasper";
			String jasperFileTwo = "src/main/resources/templates/02report_template.jasper";

			JasperReport jasperReportOne = (JasperReport) JRLoader.loadObjectFromFile(jasperFileOne);
			JasperReport jasperReportTwo = (JasperReport) JRLoader.loadObjectFromFile(jasperFileTwo);

            // 2. Proporcionar parametros si es necesario (en este ejemplo no hay)
			Map<String, Object> parameters = new HashMap<>();

			// 3. Llenar el reporte (por ejemplo, se puede conectar a una base de datos)
			JasperPrint jasperPrintOne = JasperFillManager.fillReport(jasperReportOne, parameters,
					new net.sf.jasperreports.engine.JREmptyDataSource());
			JasperPrint jasperPrintTwo = JasperFillManager.fillReport(jasperReportTwo, parameters,
					new net.sf.jasperreports.engine.JREmptyDataSource());

			// 4. Combinar los JasperPrints
			List<JasperPrint> jasperPrintList = new ArrayList<>();
			jasperPrintList.add(jasperPrintOne);
			jasperPrintList.add(jasperPrintTwo);

			// 5. Exportar el reporte a PDF
			String pdfFile = "src/main/resources/output/report1.pdf";
			JRPdfExporter exporter = new JRPdfExporter();
			exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrintList));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(pdfFile));
			exporter.exportReport();

			logger.debug("Report successfully generated at: " + pdfFile);

		} catch (JRException e) {
			logger.error("Main.generatePdfFromJasper() - JRException", e.getMessage());
			logger.error("Exception message:", e);
		} finally {
			logger.info("Main.generatePdfFromJasper() - End");
		}
	}

}
