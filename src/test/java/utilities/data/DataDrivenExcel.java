package utilities.data;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DataDrivenExcel {

	private DecimalFormat df = new DecimalFormat("0.###");
	public Map<String, String> leerExcel(Excel excel) {
		
		Map<String, String> datosExcel = new HashMap<>(); 		// Variable que contendra todas las filas
		
		try {
			FileInputStream arcExcel = new FileInputStream(new File(excel.getRutaExcel())); 			// Invocaci�n y uso del excel
			Workbook libroExcel = new XSSFWorkbook(arcExcel);
			Sheet hojaArcExcel = libroExcel.getSheet(excel.getHojaExcel()); 			// Hoja del excel a usar
			Iterator<Row> iterator = hojaArcExcel.iterator();
			ArrayList<String> cabeceras = new ArrayList<String>();
			
			while (iterator.hasNext()) { 			// Ciclo de iteraci�n por cada fila
				Row filaActual = iterator.next();
				Iterator<Cell> iteratorCelda = filaActual.iterator();
				int numFila = filaActual.getRowNum(); 				// Variable que almacenara cada fila
				
				if ((excel.isContieneCabecera() && numFila == 0) || numFila == excel.getFilaLeer()) {
					while (iteratorCelda.hasNext()) { 					// Ciclo de celdas o columnas de la hoja del excel
						Cell celdaActual = iteratorCelda.next();
						String valorCelda;
						
						switch (celdaActual.getCellType()) { 						// Validar tipo de celda para procesarla
						case NUMERIC:
							if (DateUtil.isCellDateFormatted(celdaActual)) {
								valorCelda = "" + celdaActual.getDateCellValue().getTime();
							} else {
								valorCelda = df.format(celdaActual.getNumericCellValue());
							}
							break;
						default:
							valorCelda = celdaActual.getStringCellValue();
							break;
						}
						
						if (excel.isContieneCabecera()) {						// Valida si tiene cabecera o no
							if (numFila == 0) {
								cabeceras.add(valorCelda);
							} else {
								datosExcel.put(cabeceras.get(celdaActual.getColumnIndex()), valorCelda);
							}
						} else {
							if (numFila == excel.getFilaLeer()) {
								datosExcel.put("" + celdaActual.getColumnIndex() + "", valorCelda);
							}
						}
					}
				}
			}
			libroExcel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return datosExcel;
	}
}