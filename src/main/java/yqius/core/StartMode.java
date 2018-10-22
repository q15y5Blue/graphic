package yqius.core;

import yqius.dataDeal.general.CategoryExecute;
import yqius.dataDeal.util.StrUtil;
import yqius.util.db.ConnectionPool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

@WebServlet(name = "starMode",urlPatterns = "/start")
public class StartMode extends HttpServlet {
    public static DataSource dataSource = null;
    CategoryExecute cate=new CategoryExecute();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String delete= StrUtil.trimStr(req.getParameter("delete"));
        String insert = StrUtil.trimStr(req.getParameter("insert"));
        if (!delete.equals("")){
            cate.delteData();
        }else if(!insert.equals("")){
            cate.execute();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
