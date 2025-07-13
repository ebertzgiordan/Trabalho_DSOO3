// Classe utilitária para gerar PDF com as VLANs atribuídas por porta e switch
package report;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import dao.PortaVlanDAO;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

public class RelatorioPDF {

    public static void gerarPorSwitch(int switchId, String nomeArquivo) throws Exception {
        PortaVlanDAO dao = new PortaVlanDAO();
        List<Map<String, String>> relatorio = (List<Map<String, String>>) dao.gerarRelatorioDetalhadoPorSwitch(switchId);

        if (relatorio.isEmpty()) {
            throw new Exception("Nenhum dado encontrado para este switch.");
        }

        String switchNome = relatorio.get(0).get("switch");

        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream(nomeArquivo));
        doc.open();

        Font tituloFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16);
        Font textoFont = FontFactory.getFont(FontFactory.HELVETICA, 12);

        doc.add(new Paragraph("Relatório de VLANs por Porta", tituloFont));
        doc.add(new Paragraph("Switch: " + switchNome + "\n\n", textoFont));

        for (Map<String, String> linha : relatorio) {
            String texto = String.format(
                    "Porta %s | Tipo: %s | VLANs: %s",
                    linha.get("porta"),
                    linha.get("tipo"),
                    linha.get("vlans").isEmpty() ? "Nenhuma atribuída" : linha.get("vlans")
            );
            doc.add(new Paragraph(texto, textoFont));
        }

        doc.close();
    }

}
