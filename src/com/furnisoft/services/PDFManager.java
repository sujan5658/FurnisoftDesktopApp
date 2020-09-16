
package com.furnisoft.services;

import com.furnisoft.pojos.AmountPaid;
import com.furnisoft.pojos.Bill;
import com.furnisoft.pojos.ShopDetails;
import com.furnisoft.pojos.SoldItems;
import com.itextpdf.text.Chunk;
import java.io.File;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileOutputStream;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.icepdf.ri.common.SwingController;
import org.icepdf.ri.common.SwingViewBuilder;

public class PDFManager {
    private String fileName;
    private Bill bill;
    private ShopDetails shopDetails;
    private File file;
    private boolean saveStatus;
    private String saveUpdate;
    
    public PDFManager(){
       this.saveStatus = true;
       this.fileName="";
       this.bill = new Bill();
       this.shopDetails =new ShopDetails();
    }
    private void pdfHandler() {
		try {
                        if(this.saveUpdate.equals("save")){
                            File folder = new File("pdf");
                            if (!folder.exists()) {
                                    folder.mkdir();
                            }
                            this.fileName = folder+File.separator+this.bill.getBillDate()+this.bill.getBillNo() +(int)(Math.random()*999999+1) +".pdf";
                            
                            this.file = new File(this.fileName);
                            this.file.createNewFile();
                        }
                        else if(this.saveUpdate.equals("update")){
                            this.fileName = this.bill.getBillName();
                            this.file = new File(this.fileName);
                        }

			Document document = new Document(PageSize.A5, 15f, 15f, 15f, 15f);
			FileOutputStream fout = new FileOutputStream(this.file);
			PdfWriter.getInstance(document, fout);
			document.open();
			Font font1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 14, Font.BOLD);
			Font font2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD);
			Font font3 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12);

			
			Paragraph shopName = new Paragraph(this.shopDetails.getShopName(), font1);
			Paragraph shopAddress = new Paragraph(this.shopDetails.getShopAddress(),font2);
                        Paragraph shopVatNo = new Paragraph("VAT NO : "+Long.toString(this.shopDetails.getVatNo())+ "         Bill NO : "+this.bill.getBillNo(),font2);
                        
			Paragraph billDate = new Paragraph("Billing Date : " +this.bill.getBillDate() , font3);
                        Paragraph customerName = new Paragraph("Name : " +this.bill.getCustomerName() , font3);
                        Paragraph customerAddress = new Paragraph("Address : " +this.bill.getCustomerAddress() , font3);
                        Paragraph customerContact = new Paragraph("Contact No : " +this.bill.getCustomerContactNumber() , font3);
                        
			PdfPTable table = new PdfPTable(6);
			table.setWidthPercentage(100);

			PdfPCell cell1 = new PdfPCell(shopName);
			cell1.setColspan(6);
			cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell1.setPadding(5);
			cell1.disableBorderSide(2);
			cell1.setVerticalAlignment(Element.ALIGN_CENTER);

			PdfPCell cell2 = new PdfPCell(shopAddress);
			cell2.setColspan(6);
			cell2.disableBorderSide(2);
                        cell2.disableBorderSide(1);
			cell2.setPadding(5);
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell2.setVerticalAlignment(Element.ALIGN_CENTER);
                        
                        PdfPCell cell3 = new PdfPCell(shopVatNo);
			cell3.setColspan(6);
			cell3.disableBorderSide(1);
			cell3.setPadding(5);
			cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			cell3.setVerticalAlignment(Element.ALIGN_CENTER);

                        PdfPCell cell4 = new PdfPCell(customerName);
			cell4.setColspan(3);
			cell4.disableBorderSide(1);
			cell4.setPadding(5);
			cell4.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell4.setVerticalAlignment(Element.ALIGN_CENTER);
                        
                        PdfPCell cell5 = new PdfPCell(customerAddress);
			cell5.setColspan(3);
			cell5.disableBorderSide(1);
			cell5.setPadding(5);
			cell5.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell5.setVerticalAlignment(Element.ALIGN_CENTER);
                        
                        PdfPCell cell6 = new PdfPCell(customerContact);
			cell6.setColspan(3);
			cell6.disableBorderSide(1);
			cell6.setPadding(5);
			cell6.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell6.setVerticalAlignment(Element.ALIGN_CENTER);
                        
                        PdfPCell cell7 = new PdfPCell(billDate);
			cell7.setColspan(3);
			cell7.disableBorderSide(1);
			cell7.setPadding(5);
			cell7.setHorizontalAlignment(Element.ALIGN_LEFT);
			cell7.setVerticalAlignment(Element.ALIGN_CENTER);
                        
			table.addCell(cell1);
			table.addCell(cell2);
                        table.addCell(cell3);
                        table.addCell(cell4);
                        table.addCell(cell5);
                        table.addCell(cell6);
                        table.addCell(cell7);

			PdfPCell cell8 = new PdfPCell(new Paragraph("SN", font2));
                        cell8.setPadding(5);
                        cell8.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell8.setVerticalAlignment(Element.ALIGN_CENTER);
                        
                        PdfPCell cell9 = new PdfPCell(new Paragraph("Particulars", font2));
                        cell9.setPadding(5);
                        cell9.setColspan(2);
                        cell9.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell9.setVerticalAlignment(Element.ALIGN_CENTER);
                        
                        PdfPCell cell10 = new PdfPCell(new Paragraph("Rate [RS]", font2));
                        cell10.setPadding(5);
                        cell10.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell10.setVerticalAlignment(Element.ALIGN_CENTER);
                        
                        PdfPCell cell11 = new PdfPCell(new Paragraph("Quantity", font2));
                        cell11.setPadding(5);
                        cell11.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell11.setVerticalAlignment(Element.ALIGN_CENTER);
                        
                        PdfPCell cell12 = new PdfPCell(new Paragraph("Total [RS]", font2));
                        cell12.setPadding(5);
                        cell12.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell12.setVerticalAlignment(Element.ALIGN_CENTER);
                        
                        table.addCell(cell8);
			table.addCell(cell9);
                        table.addCell(cell10);
                        table.addCell(cell11);
                        table.addCell(cell12);
                        
                        PdfPCell cell;
                        int i=0;
			for (SoldItems soldItems : this.bill.getSoldItems()) {
                                //Data Entry starts
				cell = new PdfPCell(new Paragraph(Integer.toString(i + 1), font3));
				cell.setPadding(5);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(soldItems.getHardwareName(), font3));
				cell.setPadding(5);
                                cell.setColspan(2);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(Double.toString(soldItems.getRate()), font3));
				cell.setPadding(5);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(Integer.toString(soldItems.getQuantity()), font3));
				cell.setPadding(5);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(new Paragraph(Double.toString(soldItems.getTotal()), font3));
				cell.setPadding(5);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				cell.setVerticalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);
                                i++;
			}
                        PdfPCell cell13 = new PdfPCell(new Paragraph("Sub Total", font3));
                        cell13.setPadding(5);
                        cell13.setColspan(5);
                        cell13.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell13.setVerticalAlignment(Element.ALIGN_CENTER);
                        
                        PdfPCell cell14 = new PdfPCell(new Paragraph(Double.toString(this.bill.getTotal()), font3));
                        cell14.setPadding(5);
                        cell14.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell14.setVerticalAlignment(Element.ALIGN_CENTER);
                        
                        PdfPCell cell15 = new PdfPCell(new Paragraph("Vat 13 %", font3));
                        cell15.setPadding(5);
                        cell15.setColspan(5);
                        cell15.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell15.setVerticalAlignment(Element.ALIGN_CENTER);
                        
                        PdfPCell cell16 = new PdfPCell(new Paragraph(Double.toString(this.bill.getVatAmount()), font3));
                        cell16.setPadding(5);
                        cell16.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell16.setVerticalAlignment(Element.ALIGN_CENTER);
                        
                        PdfPCell cell17 = new PdfPCell(new Paragraph("Net Total", font3));
                        cell17.setPadding(5);
                        cell17.setColspan(5);
                        cell17.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell17.setVerticalAlignment(Element.ALIGN_CENTER);
                        
                        PdfPCell cell18 = new PdfPCell(new Paragraph(Double.toString(this.bill.getNetTotal()), font3));
                        cell18.setPadding(5);
                        cell18.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell18.setVerticalAlignment(Element.ALIGN_CENTER);
                        
                        PdfPCell cell19 = new PdfPCell(new Paragraph("Discount", font3));
                        cell19.setPadding(5);
                        cell19.setColspan(5);
                        cell19.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell19.setVerticalAlignment(Element.ALIGN_CENTER);
                        
                        PdfPCell cell20 = new PdfPCell(new Paragraph(Double.toString(this.bill.getDiscountAmount()), font3));
                        cell20.setPadding(5);
                        cell20.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell20.setVerticalAlignment(Element.ALIGN_CENTER);
                        
                        PdfPCell cell21 = new PdfPCell(new Paragraph("Grand Total", font3));
                        cell21.setPadding(5);
                        cell21.setColspan(5);
                        cell21.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell21.setVerticalAlignment(Element.ALIGN_CENTER);
                        
                        PdfPCell cell22 = new PdfPCell(new Paragraph(Double.toString(this.bill.getGrandTotal()), font3));
                        cell22.setPadding(5);
                        cell22.setHorizontalAlignment(Element.ALIGN_LEFT);
                        cell22.setVerticalAlignment(Element.ALIGN_CENTER);
                        
                        table.addCell(cell13);
			table.addCell(cell14);
                        table.addCell(cell15);
                        table.addCell(cell16);
                        table.addCell(cell17);
                        table.addCell(cell18);
			table.addCell(cell19);
                        table.addCell(cell20);
                        table.addCell(cell21);
                        table.addCell(cell22);
                        document.add(table);
                        
                        
                        if(this.bill.getAmountPaid()!=null){
                            document.add(new Paragraph(""));
                            Chunk linebreak = new Chunk(new DottedLineSeparator());
                            document.add(linebreak);
                            document.add(new Paragraph(""));
                            
                            PdfPTable tbl = new PdfPTable(4);
                            tbl.setWidthPercentage(100);

                            PdfPCell cellSN = new PdfPCell(new Paragraph("SN", font2));
                            cellSN.setPadding(5);
                            cellSN.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cellSN.setVerticalAlignment(Element.ALIGN_CENTER);

                            PdfPCell cellDate = new PdfPCell(new Paragraph("Date", font2));
                            cellDate.setPadding(5);
                            cellDate.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cellDate.setVerticalAlignment(Element.ALIGN_CENTER);

                            PdfPCell cellAmountPaid = new PdfPCell(new Paragraph("Amount Paid", font2));
                            cellAmountPaid.setPadding(5);
                            cellAmountPaid.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cellAmountPaid.setVerticalAlignment(Element.ALIGN_CENTER);

                            PdfPCell cellAmountDue = new PdfPCell(new Paragraph("Amount Due", font2));
                            cellAmountDue.setPadding(5);
                            cellAmountDue.setHorizontalAlignment(Element.ALIGN_LEFT);
                            cellAmountDue.setVerticalAlignment(Element.ALIGN_CENTER);

                            tbl.addCell(cellSN);
                            tbl.addCell(cellDate);
                            tbl.addCell(cellAmountPaid);
                            tbl.addCell(cellAmountDue);

                            i=0;
                            for(AmountPaid amountPaid : this.bill.getAmountPaid()){
                                cell = new PdfPCell(new Paragraph(Integer.toString(i + 1), font3));
                                    cell.setPadding(5);
                                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    cell.setVerticalAlignment(Element.ALIGN_CENTER);
                                    tbl.addCell(cell);

                                    cell = new PdfPCell(new Paragraph(amountPaid.getDate(), font3));
                                    cell.setPadding(5);
                                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    cell.setVerticalAlignment(Element.ALIGN_CENTER);
                                    tbl.addCell(cell);

                                    cell = new PdfPCell(new Paragraph(Double.toString(amountPaid.getAmountPaid()), font3));
                                    cell.setPadding(5);
                                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    cell.setVerticalAlignment(Element.ALIGN_CENTER);
                                    tbl.addCell(cell);

                                    cell = new PdfPCell(new Paragraph(Double.toString(amountPaid.getAmountDue()), font3));
                                    cell.setPadding(5);
                                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                                    cell.setVerticalAlignment(Element.ALIGN_CENTER);
                                    tbl.addCell(cell);
                                    i++;
                            }
                            document.add(tbl);
                        }
			document.close();
		} catch (Exception er) {
                        this.saveStatus = false;
			System.out.println("Error PDF : " + er.getMessage());
		}
	}
    private void PdfViewer() {
		JFrame frame;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int) (screenSize.getHeight());
		int width = (int) (screenSize.getWidth());
		frame = new JFrame();
		frame.setSize(width, height);
		frame.setResizable(true);
		frame.setTitle("PDF View");
		frame.setLocationRelativeTo(null);

		SwingController controller = new SwingController();

		SwingViewBuilder factory = new SwingViewBuilder(controller);

		JPanel viewerComponentPanel = factory.buildViewerPanel();

		controller.getDocumentViewController().setAnnotationCallback(
				new org.icepdf.ri.common.MyAnnotationCallback(controller.getDocumentViewController()));
		frame.getContentPane().add(viewerComponentPanel);
		frame.add(viewerComponentPanel);

		controller.openDocument(this.fileName);

		frame.pack();
		frame.setVisible(true);
                frame.setLocationRelativeTo(null);
	}
    public boolean saveBill(Bill bill,ShopDetails shopDetails){
       this.bill = bill;
       this.shopDetails = shopDetails;
       this.saveUpdate = "save";
       this.pdfHandler();
       return this.saveStatus;
    }
    public void showBill(){
        this.PdfViewer();
    }
    public boolean updateBill(Bill bill,ShopDetails shopDetails){
        this.saveUpdate = "update";
        this.bill = bill;
        this.shopDetails = shopDetails;
        this.pdfHandler();
        return this.saveStatus;
    }
    public void setFileName(String fileName){
        this.fileName = fileName;
    }
    public String getFileName(){
        return this.fileName;
    }
}
