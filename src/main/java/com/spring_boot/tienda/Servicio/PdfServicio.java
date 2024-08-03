package com.spring_boot.tienda.Servicio;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.spring_boot.tienda.Entidad.Productos;
import com.spring_boot.tienda.Repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
@Service
public class PdfServicio {

    @Autowired
    private ProductoRepository productoRepository;

    public byte[] generarPdf() throws DocumentException, IOException {
        List<Productos> productos = productoRepository.findAll();
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();
        document.add(new Paragraph("Listado de Productos", FontFactory.getFont("Arial", 14, Font.BOLD))); //clase recupera fuentes
        PdfPTable table = new PdfPTable(5); // Crea tablas
        table.setWidthPercentage(100);
        table.addCell(new PdfPCell(new Phrase("Código", FontFactory.getFont("Arial", 12))));
        table.addCell(new PdfPCell(new Phrase("Nombre", FontFactory.getFont("Arial", 12))));
        table.addCell(new PdfPCell(new Phrase("Descripción", FontFactory.getFont("Arial", 12))));
        table.addCell(new PdfPCell(new Phrase("Precio", FontFactory.getFont("Arial", 12))));
        table.addCell(new PdfPCell(new Phrase("Stock", FontFactory.getFont("Arial", 12))));
        for (Productos producto : productos) {
            table.addCell(new PdfPCell(new Phrase(String.valueOf(producto.getId()), FontFactory.getFont("Arial", 12))));
            table.addCell(new PdfPCell(new Phrase(producto.getNombre(), FontFactory.getFont("Arial", 12))));
            table.addCell(new PdfPCell(new Phrase(producto.getDescripcion(), FontFactory.getFont("Arial", 12)))); // Agregado
            table.addCell(new PdfPCell(new Phrase(String.valueOf(producto.getPrecio()), FontFactory.getFont("Arial", 12))));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(producto.getStock()), FontFactory.getFont("Arial", 12)))); // Agregado
        }
        document.add(table);
        document.close();
        return baos.toByteArray();
    }
}
