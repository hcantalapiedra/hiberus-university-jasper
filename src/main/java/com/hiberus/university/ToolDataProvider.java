package com.hiberus.university;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ToolDataProvider {

	private static final Logger logger = LogManager.getLogger(ToolDataProvider.class);

	public static List<Tools> generateToolsList() {
		try {
			logger.info("ToolDataProvider.generateToolsList() - Init");
			List<Tools> toolsList = new ArrayList<>();

			toolsList.add(new Tools(1, "Hammer", "Used for hammering nails"));
			toolsList.add(new Tools(2, "Screwdriver", "Used for driving screws"));
			toolsList.add(new Tools(3, "Wrench", "Used for gripping and turning nuts"));
			toolsList.add(new Tools(4, "Pliers", "Used for holding objects firmly"));
			toolsList.add(new Tools(5, "Saw", "Used for cutting wood"));
			toolsList.add(new Tools(6, "Drill", "Used for making holes"));
			toolsList.add(new Tools(7, "Chisel", "Used for carving or cutting hard materials"));
			toolsList.add(new Tools(8, "Level", "Used for checking horizontal alignment"));
			toolsList.add(new Tools(9, "Tape Measure", "Used for measuring distances"));
			toolsList.add(new Tools(10, "Sledgehammer", "Used for heavy-duty hammering tasks"));
			toolsList.add(new Tools(11, "Utility Knife", "Used for cutting various materials"));
			toolsList.add(new Tools(12, "Clamp", "Used for holding objects securely in place"));
			toolsList.add(new Tools(13, "Socket Wrench", "Used for turning bolts with different sizes"));
			toolsList.add(new Tools(14, "Allen Wrench", "Used for driving bolts and screws with hexagonal sockets"));
			toolsList.add(new Tools(15, "Handsaw", "Used for manual wood cutting"));
			toolsList.add(new Tools(16, "Mallet", "Used for striking without damaging surface"));
			toolsList.add(new Tools(17, "Flashlight", "Used for illumination in dark areas"));
			toolsList.add(new Tools(18, "Paintbrush", "Used for applying paint or varnish"));
			toolsList.add(new Tools(19, "Spade", "Used for digging and moving soil"));
			toolsList.add(new Tools(20, "Wheelbarrow", "Used for transporting materials in construction"));

			// Crear mas elementos dinamicamente
			for (int i = 21; i <= 120; i++) {
				toolsList.add(new Tools(i, "Tool " + i, "Description of Tool " + i));
			}

			return toolsList;
		} finally {
			logger.info("ToolDataProvider.generateToolsList() - End");
		}
	}
}