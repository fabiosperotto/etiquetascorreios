package com.correios.services;

import com.correios.entities.Endereco;
import com.correios.entities.Pessoa;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.Leading;
import com.itextpdf.layout.properties.Property;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe geradora de etiqueta para impressao, usa iText como dependencia
 * @version 1.0
 * @author fabiosperotto
 */
public class Etiqueta {

    private String destinoArquivo;
    private float alturaDocumento;
    private float larguraDocumento;
    private Pessoa destinatario;
    private Endereco endereco;

    /**
     * Construtor da clase que configura local e dimensoes do arquivo PDF a ser gerado
     * @param destinatario
     * @param endereco 
     */
    public Etiqueta(Pessoa destinatario, Endereco endereco) {
        this.destinatario = destinatario;
        this.endereco = endereco;
        this.destinoArquivo = "./etiqueta.pdf"; //raiz do projeto
        this.alturaDocumento = (float) 138.0;
        this.larguraDocumento = (float) 106.0;
    }

    public String getDestinoArquivo() {
        return destinoArquivo;
    }

    public void setDestinoArquivo(String destinoArquivo) {
        this.destinoArquivo = destinoArquivo;
    }

    public float getAlturaDocumento() {
        return alturaDocumento;
    }

    public void setAlturaDocumento(float alturaDocumento) {
        this.alturaDocumento = alturaDocumento;
    }

    /**
     * Realiza a configuracao do descritor de arquivos que ira gerar o PDF
     * @see PdfWriter
     * @return Object descritor de arquivo pronto para uso
     */
    public Object configurarGeradorPDF() {

        PdfWriter writer = null;
        try {
            writer = new PdfWriter(this.destinoArquivo);
            return writer;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Etiqueta.class.getName()).log(Level.SEVERE, null, ex);
        }
        return writer;
    }

    /**
     * Realiza a geracao do PDF de acordo com elementos estruturais para uma etiqueta de enderecamento
     * @return 
     */
    public boolean gerarPDFEndereco() {
        try {
            PdfWriter writer = (PdfWriter) configurarGeradorPDF(); //substituir o cast para outras libs de acordo

            PdfDocument pdf = new PdfDocument(writer);
            Rectangle retangulo = new Rectangle(this.larguraDocumento, this.alturaDocumento);
            PageSize pagesize = new PageSize(retangulo);
            Document documento = new Document(pdf, pagesize);
            documento.setMargins(01, 04, 01, 04);
            PdfFont textoPadrao = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
            PdfFont titulo = PdfFontFactory.createFont(StandardFonts.TIMES_BOLD);

            //configs padrao de texto, outras configs serao informadas de acordo com cada secao do documento
            documento.setFont(textoPadrao);
            documento.setFontSize(5);
            documento.setProperty(Property.LEADING, new Leading(Leading.MULTIPLIED, 0.2f)); //leading = entrelinhas (espacamento)

            //linhas de separacao de secoes e de corte de impressora
            Paragraph linhaCabecalho = new Paragraph("________________________________")
                    .setFont(titulo)
                    .setFontSize(6);
            Paragraph linhaRodape = new Paragraph("---------------------------------------------------------------------")
                    .setFont(titulo)
                    .setFontSize(4);
            
            Paragraph destinatario = new Paragraph("Destinatário").setBold(); 
            Paragraph dadosDestinatario = new Paragraph(this.destinatario.getNome());
            Paragraph dadosEndereco = new Paragraph(this.endereco.getLogradouro() + ", "+this.endereco.getNumero());

           
            //organizacao final das linhas de impressao 
            ImageData dadosImagemLogo = ImageDataFactory.create(getClass().getResource("/img/correios-logo.png"));
            Image logo = new Image(dadosImagemLogo); 
            documento.add(logo);
            documento.add(linhaCabecalho);
            documento.add(destinatario);            
            documento.add(dadosDestinatario);
            documento.add(dadosEndereco);          
            documento.add(linhaRodape);
            documento.close(); //documento impresso
            return true;

        } catch (IOException ex) {            
            Logger.getLogger(Etiqueta.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
