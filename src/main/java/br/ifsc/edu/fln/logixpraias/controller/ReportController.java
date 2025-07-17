package br.ifsc.edu.fln.logixpraias.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Value;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReportController {
    @Autowired
    private DataSource dataSource;
    @GetMapping()
    public ModelAndView page() {
        ModelAndView mv = new ModelAndView("reports");
        return mv;
    }

    @GetMapping("/receipts")
    public void gerarRelatorioRecebimentos(HttpServletResponse response) throws JRException, IOException, SQLException {
        // 1. Carrega o arquivo .jasper
        URL url = getClass().getResource("/reports/recebimento_materiais.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

        // 2. Cria conexão com o banco de dados
        Connection conn = dataSource.getConnection();

        // 4. Preenche o relatório
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

        // 5. Define tipo e cabeçalhos da resposta
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=relatorio_recebimentos.pdf");

        // 6. Exporta o relatório diretamente na resposta HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        // 7. Fecha conexão
        conn.close();
    }
    @GetMapping("/withdrawals")
    public void gerarRelatorioRetiradas(HttpServletResponse response) throws JRException, IOException, SQLException {
        // 1. Carrega o arquivo .jasper
        URL url = getClass().getResource("/reports/retiradas_materiais.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

        // 2. Cria conexão com o banco de dados
        Connection conn = dataSource.getConnection();

        // 4. Preenche o relatório
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

        // 5. Define tipo e cabeçalhos da resposta
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=relatorio_retiradas.pdf");

        // 6. Exporta o relatório diretamente na resposta HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        // 7. Fecha conexão
        conn.close();
    }

    @GetMapping("/storage")
    public void gerarRelatorioEstoque(HttpServletResponse response) throws JRException, IOException, SQLException {
        // 1. Carrega o arquivo .jasper
        URL url = getClass().getResource("/reports/estoque.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

        // 2. Cria conexão com o banco de dados
        Connection conn = dataSource.getConnection();

        // 4. Preenche o relatório
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

        // 5. Define tipo e cabeçalhos da resposta
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=relatorio_estoque.pdf");

        // 6. Exporta o relatório diretamente na resposta HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        // 7. Fecha conexão
        conn.close();
    }

    @GetMapping("/recent-transactions")
    public void gerarRelatorioMovimentacaoMes(HttpServletResponse response) throws JRException, IOException, SQLException {
        // 1. Carrega o arquivo .jasper
        URL url = getClass().getResource("/reports/movimentacao_mensal.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

        // 2. Cria conexão com o banco de dados
        Connection conn = dataSource.getConnection();

        // 4. Preenche o relatório
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

        // 5. Define tipo e cabeçalhos da resposta
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=movimentacao_30_dias.pdf");

        // 6. Exporta o relatório diretamente na resposta HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        // 7. Fecha conexão
        conn.close();
    }
    @GetMapping("/transaction-by-user")
    public void gerarRelatorioMovimentacaoUsuario(HttpServletResponse response) throws JRException, IOException, SQLException {
        // 1. Carrega o arquivo .jasper
        URL url = getClass().getResource("/reports/movimentacao_usuario.jasper");
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(url);

        // 2. Cria conexão com o banco de dados
        Connection conn = dataSource.getConnection();

        // 4. Preenche o relatório
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, conn);

        // 5. Define tipo e cabeçalhos da resposta
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=movimentacao_total_usuarios.pdf");

        // 6. Exporta o relatório diretamente na resposta HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        // 7. Fecha conexão
        conn.close();
    }
}
