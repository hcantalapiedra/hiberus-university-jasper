package com.hiberus.university;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

public class Main {

	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {

        // 1. Generar el documento PDF con un fichero compilado (.jasper)
        generatePdfFromJasper();

	}

	private static void generatePdfFromJasper() {
		try {
			logger.info("Main.generatePdfFromJasper() - Init");

            // 1. Cargar el archivo .jasper ya compilado
            String jasperFile = "src/main/resources/templates/report_template.jasper";
            JasperReport jasperReport = (JasperReport) JRLoader.loadObjectFromFile(jasperFile);

			// 2. Crear el bean con el valor de varOne
			Map<String, Object> fieldList = new HashMap<>();
			fieldList.put("varOne", "Hiberus University");
			fieldList.put("varTwo", "Hiberus University Bold");
			fieldList.put("varThree", null);

			// 3. Crear una lista de datos para el reporte
			List<Map<String, Object>> dataList = new ArrayList<>();
			dataList.add(fieldList);

			// 4. Crear un JRDataSource basado en la lista de datos
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

			// 5. Proporcionar parametros
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("imgCabecera", "src/main/resources/images/logo.png");

			// 6. Llenar el reporte (por ejemplo, se puede conectar a una base de datos)
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

			// 7. Exportar el reporte a PDF
			String pdfFile = "src/main/resources/output/report1.pdf";
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
