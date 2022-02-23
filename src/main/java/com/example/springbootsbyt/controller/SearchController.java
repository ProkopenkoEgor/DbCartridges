package com.example.springbootsbyt.controller;

import com.example.springbootsbyt.model.*;
import com.example.springbootsbyt.service.impl.*;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Controller
public class SearchController {
    private final CartridgeServiceImpl cartridgeServiceImpl;
    private final CartrsServiceImpl cartrsServiceImpl;
    private final HistoryServiceImpl historyServiceImpl;
    private final PrintersServiceImpl printersServiceImpl;
    private final ManufacturerServiceImpl manufacturerServiceImpl;
    private final PartyLotsServiceImpl partyLotsServiceImpl;


    @Autowired
    public SearchController(CartridgeServiceImpl cartridgeServiceImpl,
                            CartrsServiceImpl cartrsServiceImpl,
                            HistoryServiceImpl historyServiceImpl,
                            PrintersServiceImpl printersServiceImpl, ManufacturerServiceImpl manufacturerServiceImpl, PartyLotsServiceImpl partyLotsServiceImpl){
        this.cartridgeServiceImpl = cartridgeServiceImpl;
        this.cartrsServiceImpl = cartrsServiceImpl;
        this.historyServiceImpl = historyServiceImpl;
        this.printersServiceImpl = printersServiceImpl;
        this.manufacturerServiceImpl = manufacturerServiceImpl;
        this.partyLotsServiceImpl = partyLotsServiceImpl;
    }
    @GetMapping("/search")
    public String findAllSearch() {
        return "search";
    }

    @PostMapping("/Data-Search-Dispose")
    public String DataSearchDispose(@Param("dt1") Date dt1,@Param("dt2") Date dt2, Model model, @Param("firstSelect") String firstSelect,
                                    @Param("partylotsSelect") String partylotsSelect){
        if(partylotsSelect != null){
            if(firstSelect.equals("1")){
                List<Partylots> partylots1 = partyLotsServiceImpl.findDsLotNumberByPartyStatus0Or1();
                List<History> histories = historyServiceImpl.findByDateOfStatusBetweenDate1AndDate2(dt1, dt2);
                model.addAttribute("history", histories);
                model.addAttribute("partylots1", partylots1);
                return "result-DataSearch";
            }
            if(firstSelect.equals("2")){
                List<Partylots> partylots1 = partyLotsServiceImpl.findDsLotNumberByPartyStatus2();
                List<History> histories = historyServiceImpl.findByDateOfStatusBetweenDate1AndDate2(dt1, dt2);
                model.addAttribute("history", histories);
                model.addAttribute("partylots1", partylots1);
                return "result-DataSearch";
            }
            if(firstSelect.equals("3")){
                List<Partylots> partylots1 = partyLotsServiceImpl.findDsLotNumberByPartyStatus3Or4();
                List<History> histories = historyServiceImpl.findByDateOfStatusBetweenDate1AndDate2(dt1, dt2);
                model.addAttribute("history", histories);
                model.addAttribute("partylots1", partylots1);
                return "result-DataSearch";
            }
            if(firstSelect.equals("4")){
                List<Partylots> partylots1 = partyLotsServiceImpl.findDsLotNumberByPartyStatus5();
                List<History> histories = historyServiceImpl.findByDateOfStatusBetweenDate1AndDate2(dt1, dt2);
                model.addAttribute("history", histories);
                model.addAttribute("partylots1", partylots1);
                return "result-DataSearch";
            }
            if(firstSelect.equals("5")){
                List<Partylots> partylots1 = partyLotsServiceImpl.findDsLotNumber();
                List<History> histories = historyServiceImpl.findByDateOfStatusBetweenDate1AndDate2(dt1, dt2);
                model.addAttribute("history", histories);
                model.addAttribute("partylots1", partylots1);
                return "result-DataSearch";
            }
        }
        if (firstSelect.equals("1")){
            List<Cartridges> cartridges = cartridgeServiceImpl.findAllByPartyStatus0(dt1, dt2);
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            List<Printers> printers = printersServiceImpl.findAll();
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            List<Partylots> partylots = partyLotsServiceImpl.findDsCartridgesId();
            List<History> history = historyServiceImpl.findAll();
            model.addAttribute("cartridges", cartridges);
            model.addAttribute("cartrs", cartrs);
            model.addAttribute("printers", printers);
            model.addAttribute("manufacturers",manufacturers);
            model.addAttribute("partylots", partylots);
            model.addAttribute("history", history);
            model.addAttribute("firstSelect", firstSelect);
            model.addAttribute("dt1", dt1);
            model.addAttribute("dt2", dt2);
            return "searchForUsedCartridges";
        }
        if (firstSelect.equals("2")){
            List<Cartridges> cartridges = cartridgeServiceImpl.findAllByPartyStatus1And2(dt1, dt2);
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            List<Printers> printers = printersServiceImpl.findAll();
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            List<Partylots> partylots = partyLotsServiceImpl.findDsCartridgesId();
            List<History> history = historyServiceImpl.findAll();
            model.addAttribute("cartridges", cartridges);
            model.addAttribute("cartrs", cartrs);
            model.addAttribute("printers", printers);
            model.addAttribute("manufacturers",manufacturers);
            model.addAttribute("partylots", partylots);
            model.addAttribute("history", history);
            model.addAttribute("firstSelect", firstSelect);
            model.addAttribute("dt1", dt1);
            model.addAttribute("dt2", dt2);
            return "searchForUsedCartridges";
        }
        if (firstSelect.equals("3")){
            List<Cartridges> cartridges = cartridgeServiceImpl.findAllByPartyStatus3(dt1, dt2);
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            List<Printers> printers = printersServiceImpl.findAll();
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            List<Partylots> partylots = partyLotsServiceImpl.findDsCartridgesId();
            List<History> history = historyServiceImpl.findAll();
            model.addAttribute("cartridges", cartridges);
            model.addAttribute("cartrs", cartrs);
            model.addAttribute("printers", printers);
            model.addAttribute("manufacturers",manufacturers);
            model.addAttribute("partylots", partylots);
            model.addAttribute("history", history);
            model.addAttribute("firstSelect", firstSelect);
            model.addAttribute("dt1", dt1);
            model.addAttribute("dt2", dt2);
            return "searchForUsedCartridges";
        }
        if (firstSelect.equals("4")){
            List<Cartridges> cartridges = cartridgeServiceImpl.findAllByPartyStatus4And5(dt1, dt2);
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            List<Printers> printers = printersServiceImpl.findAll();
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            List<Partylots> partylots = partyLotsServiceImpl.findDsCartridgesId();
            List<History> history = historyServiceImpl.findAll();
            model.addAttribute("cartridges", cartridges);
            model.addAttribute("cartrs", cartrs);
            model.addAttribute("printers", printers);
            model.addAttribute("manufacturers",manufacturers);
            model.addAttribute("partylots", partylots);
            model.addAttribute("history", history);
            model.addAttribute("firstSelect", firstSelect);
            model.addAttribute("dt1", dt1);
            model.addAttribute("dt2", dt2);
            return "searchForUsedCartridges";
        }
        if (firstSelect.equals("5")){
            List<Cartridges> cartridges = cartridgeServiceImpl.findAllByPartyStatus(dt1,dt2);
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            List<Printers> printers = printersServiceImpl.findAll();
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            List<Partylots> partylots = partyLotsServiceImpl.findDsCartridgesId();
            List<History> history = historyServiceImpl.findAll();
            model.addAttribute("cartridges", cartridges);
            model.addAttribute("cartrs", cartrs);
            model.addAttribute("printers", printers);
            model.addAttribute("manufacturers",manufacturers);
            model.addAttribute("partylots", partylots);
            model.addAttribute("history", history);
            model.addAttribute("firstSelect", firstSelect);
            model.addAttribute("dt1", dt1);
            model.addAttribute("dt2", dt2);
            return "searchForUsedCartridges";
        }
        return "searchForUsedCartridges";
    }

    //Стиль для названия колонок
    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop (BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setWrapText(true);
        return style;
    }

    @GetMapping("/cartridge-Export-Excel-CartridgesBySearch/{firstSelect}/{dt1}/{dt2}")
    public String cartridgeExportExcelCartridgesBySearch(@PathVariable("firstSelect") String firstSelect,@PathVariable("dt1") Date dt1,
                                                         @PathVariable("dt2") Date dt2, Model model) throws IOException {
        List<Partylots> partylots = partyLotsServiceImpl.findDsCartridgesId();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Список картриджей по поиску");
        Cell cell;
        Row row,row1,row3;
        //Создание стилей
        HSSFCellStyle style = createStyleForTitle(workbook);
        HSSFCellStyle style2 = workbook.createCellStyle();
        HSSFCellStyle style3 = workbook.createCellStyle();
        HSSFCellStyle style4 = workbook.createCellStyle();
        HSSFCellStyle style5 = workbook.createCellStyle();
        HSSFCellStyle style6 = workbook.createCellStyle();
        HSSFCellStyle style7 = workbook.createCellStyle();
        style2.setAlignment(HorizontalAlignment.RIGHT);
        style2.setWrapText(true);
        style3.setAlignment(HorizontalAlignment.CENTER);
        style3.setWrapText(true);
        HSSFFont font1 = workbook.createFont();
        font1.setBold(true);
        style3.setFont(font1);
        style4.setAlignment(HorizontalAlignment.CENTER);
        style5.setAlignment(HorizontalAlignment.RIGHT);
        style5.setFont(font1);
        style6.setAlignment(HorizontalAlignment.LEFT);
        style6.setFont(font1);
        style7.setAlignment(HorizontalAlignment.LEFT);
        //Для даты в шапке
        DataFormat format = workbook.createDataFormat();
        CellStyle dateStyle = workbook.createCellStyle();
        dateStyle.setDataFormat(format.getFormat("dd.mm.yyyy"));
        dateStyle.setAlignment(HorizontalAlignment.LEFT);
        //Для даты внутри таблицы
        DataFormat format1 = workbook.createDataFormat();
        CellStyle dateStyle1 = workbook.createCellStyle();
        dateStyle1.setDataFormat(format1.getFormat("dd.mm.yyyy"));
        dateStyle1.setAlignment(HorizontalAlignment.CENTER);
        dateStyle1.setVerticalAlignment(VerticalAlignment.CENTER);
        dateStyle1.setBorderBottom(BorderStyle.THIN);
        dateStyle1.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        dateStyle1.setBorderLeft(BorderStyle.THIN);
        dateStyle1.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        dateStyle1.setBorderRight(BorderStyle.THIN);
        dateStyle1.setRightBorderColor(IndexedColors.BLACK.getIndex());
        dateStyle1.setBorderTop (BorderStyle.THIN);
        dateStyle1.setTopBorderColor(IndexedColors.BLACK.getIndex());
        //Создание строк

        row1 = sheet.createRow(0);
        row3 = sheet.createRow(2);

        //Создание колонок
        cell = row1.createCell(8,CellType.STRING);
        cell.setCellValue("РУП 'Минскэнерго' филиал 'Энергосбыт'");
        cell.setCellStyle(style2);

        cell = row1.createCell(0);
        cell.setCellValue(Date.valueOf(LocalDate.now()));
        cell.setCellStyle(dateStyle);

        cell = row3.createCell(0, CellType.STRING);
        cell.setCellValue("№ п/п");
        cell.setCellStyle(style);

        cell = row3.createCell(1, CellType.STRING);
        cell.setCellValue("Статус");
        cell.setCellStyle(style);

        cell = row3.createCell(2, CellType.STRING);
        cell.setCellValue("Дата принятия");
        cell.setCellStyle(style);

        cell = row3.createCell(3, CellType.STRING);
        cell.setCellValue("Модель принтера");
        cell.setCellStyle(style);

        cell = row3.createCell(4, CellType.STRING);
        cell.setCellValue("Производитель");
        cell.setCellStyle(style);

        cell = row3.createCell(5, CellType.STRING);
        cell.setCellValue("Инвентарный номер");
        cell.setCellStyle(style);

        cell = row3.createCell(6, CellType.STRING);
        cell.setCellValue("Город");
        cell.setCellStyle(style);

        cell = row3.createCell(7, CellType.STRING);
        cell.setCellValue("Количество заправок");
        cell.setCellStyle(style);

        cell = row3.createCell(8, CellType.STRING);
        cell.setCellValue("Примечания");
        cell.setCellStyle(style);

        //Границы для стиля style1
        HSSFCellStyle style1 = workbook.createCellStyle();
        style1.setVerticalAlignment(VerticalAlignment.CENTER);
        style1.setAlignment(HorizontalAlignment.CENTER);
        style1.setBorderBottom(BorderStyle.THIN);
        style1.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style1.setBorderLeft(BorderStyle.THIN);
        style1.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style1.setBorderRight(BorderStyle.THIN);
        style1.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style1.setBorderTop (BorderStyle.THIN);
        style1.setTopBorderColor(IndexedColors.BLACK.getIndex());
        //Загрузка данных на Эксель
        int rownum = 2;
        for (Partylots partylots1 : partylots) {
            Cartridges cartridges1 = cartridgeServiceImpl.findById(partylots1.getCartridgesId());
            Cartrs cartrs1 = cartrsServiceImpl.findById(cartridges1.getCartrsIdCartrs());
            Printers printers1 = printersServiceImpl.findById(cartrs1.getPrintersIdPrinters());
            Manufacturers manufacturers1 = manufacturerServiceImpl.findById(printers1.getModelsIdModels());

            rownum++;
            row = sheet.createRow(rownum);

            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue(rownum-2);
            cell.setCellStyle(style1);

            cell = row.createCell(1, CellType.STRING);
            if(partylots1.getPartyStatus() == 0){
                cell.setCellValue("в заправке");
            }else
            if(partylots1.getPartyStatus() == 1){
                cell.setCellValue("в работе");
            }else
            if(partylots1.getPartyStatus() == 2){
                cell.setCellValue("в работе");
            }else
            if(partylots1.getPartyStatus() == 3){
                cell.setCellValue("на списании");
            }else
            if(partylots1.getPartyStatus() == 4){
                cell.setCellValue("утилизирован");
            }else
            if(partylots1.getPartyStatus() == 5){
                cell.setCellValue("утилизирован");
            }
            cell.setCellStyle(style1);

            if (partylots1.getHistoryIdHistoryReturn() != null){
                History history1 = historyServiceImpl.findById(partylots1.getHistoryIdHistoryReturn());
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(history1.getDateOfStatus());
                cell.setCellStyle(dateStyle1);
            }else {
                History history1 = historyServiceImpl.findById(partylots1.getHistoryIdHistory());
                cell = row.createCell(2);
                cell.setCellValue(history1.getDateOfStatus());
                cell.setCellStyle(dateStyle1);
            }

            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(printers1.getTypePrinters());
            cell.setCellStyle(style1);

            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue(manufacturers1.getModelFromPrinters());
            cell.setCellStyle(style1);

            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue(cartridges1.getInventoryNumber());
            cell.setCellStyle(style1);

            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue(cartridges1.getCity());
            cell.setCellStyle(style1);

            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue(cartridges1.getCount());
            cell.setCellStyle(style1);

            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue(partylots1.getPartyComments());
            cell.setCellStyle(style1);
        }

        //Выставление печати на принтер для листа(sheet) формат а4 , и чтобы в одну страницу печатало
        sheet.getPrintSetup().setPaperSize(PrintSetup.A4_PAPERSIZE);
        sheet.getPrintSetup().setFitHeight((short)1);
        sheet.getPrintSetup().setFitWidth((short)1);
        sheet.setAutobreaks(true);
        File file = new File("C:/demo/Список картриджей по поиску.xls");
        file.getParentFile().mkdirs();
        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);
        sheet.autoSizeColumn(5);
        sheet.autoSizeColumn(6);
        sheet.autoSizeColumn(7);
        sheet.autoSizeColumn(8);
        //Запись всех данных в эксель
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        //Закрытие экселя для его редактирования и удаления
        workbook.close();
        outFile.close();
        if (firstSelect.equals("1")) {
            List<Cartridges> cartridges = cartridgeServiceImpl.findAllByPartyStatus0(dt1, dt2);
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            List<Printers> printers = printersServiceImpl.findAll();
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            List<Partylots> partylots1 = partyLotsServiceImpl.findDsCartridgesId();
            List<History> history1 = historyServiceImpl.findAll();
            model.addAttribute("cartridges", cartridges);
            model.addAttribute("cartrs", cartrs);
            model.addAttribute("printers", printers);
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("partylots", partylots1);
            model.addAttribute("history", history1);
            model.addAttribute("firstSelect", firstSelect);
            model.addAttribute("dt1", dt1);
            model.addAttribute("dt2", dt2);
            return "searchForUsedCartridges";
        }
        if (firstSelect.equals("2")) {
            List<Cartridges> cartridges = cartridgeServiceImpl.findAllByPartyStatus1And2(dt1, dt2);
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            List<Printers> printers = printersServiceImpl.findAll();
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            List<Partylots> partylots1 = partyLotsServiceImpl.findDsCartridgesId();
            List<History> history1 = historyServiceImpl.findAll();
            model.addAttribute("cartridges", cartridges);
            model.addAttribute("cartrs", cartrs);
            model.addAttribute("printers", printers);
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("partylots", partylots1);
            model.addAttribute("history", history1);
            model.addAttribute("firstSelect", firstSelect);
            model.addAttribute("dt1", dt1);
            model.addAttribute("dt2", dt2);
            return "searchForUsedCartridges";
        }
        if (firstSelect.equals("3")) {
            List<Cartridges> cartridges = cartridgeServiceImpl.findAllByPartyStatus3(dt1, dt2);
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            List<Printers> printers = printersServiceImpl.findAll();
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            List<Partylots> partylots1 = partyLotsServiceImpl.findDsCartridgesId();
            List<History> history1 = historyServiceImpl.findAll();
            model.addAttribute("cartridges", cartridges);
            model.addAttribute("cartrs", cartrs);
            model.addAttribute("printers", printers);
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("partylots", partylots1);
            model.addAttribute("history", history1);
            model.addAttribute("firstSelect", firstSelect);
            model.addAttribute("dt1", dt1);
            model.addAttribute("dt2", dt2);
            return "searchForUsedCartridges";
        }
        if (firstSelect.equals("4")) {
            List<Cartridges> cartridges = cartridgeServiceImpl.findAllByPartyStatus4And5(dt1, dt2);
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            List<Printers> printers = printersServiceImpl.findAll();
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            List<Partylots> partylots1 = partyLotsServiceImpl.findDsCartridgesId();
            List<History> history1 = historyServiceImpl.findAll();
            model.addAttribute("cartridges", cartridges);
            model.addAttribute("cartrs", cartrs);
            model.addAttribute("printers", printers);
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("partylots", partylots1);
            model.addAttribute("history", history1);
            model.addAttribute("firstSelect", firstSelect);
            model.addAttribute("dt1", dt1);
            model.addAttribute("dt2", dt2);
            return "searchForUsedCartridges";
        }
        if (firstSelect.equals("5")) {
            List<Cartridges> cartridges = cartridgeServiceImpl.findAllByPartyStatus(dt1, dt2);
            List<Cartrs> cartrs = cartrsServiceImpl.findAll();
            List<Printers> printers = printersServiceImpl.findAll();
            List<Manufacturers> manufacturers = manufacturerServiceImpl.findAll();
            List<Partylots> partylots1 = partyLotsServiceImpl.findDsCartridgesId();
            List<History> history1 = historyServiceImpl.findAll();
            model.addAttribute("cartridges", cartridges);
            model.addAttribute("cartrs", cartrs);
            model.addAttribute("printers", printers);
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("partylots", partylots1);
            model.addAttribute("history", history1);
            model.addAttribute("firstSelect", firstSelect);
            model.addAttribute("dt1", dt1);
            model.addAttribute("dt2", dt2);
            return "searchForUsedCartridges";
        }
        return "redirect:/main";
    }
}
