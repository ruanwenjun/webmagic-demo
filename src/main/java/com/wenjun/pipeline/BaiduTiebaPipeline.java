package com.wenjun.pipeline;

import com.wenjun.entity.CSUEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class BaiduTiebaPipeline implements Pipeline {

    private Logger logger = LoggerFactory.getLogger(BaiduTiebaPipeline.class);

    public void process(ResultItems resultItems, Task task) {
        List<CSUEntity> list = resultItems.get("list");

        try (Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/localhost",
                "root",
                "ruanwenjun")) {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO crawl_baidu_hubu_tieba (author,title,comment_num) VALUES (?,?,?)");
            logger.info("begin insert {} entity into mysql", list.size());
            for (int i = 0; i < list.size(); i++) {
                CSUEntity csuEntity = list.get(i);
                try {
                    preparedStatement.setString(1, csuEntity.getAuthor());
                    preparedStatement.setString(2, csuEntity.getTitle());
                    preparedStatement.setInt(3, csuEntity.getCommentNum());
                    preparedStatement.execute();
                } catch (Exception e) {
                    logger.error("insert into mysql error,author:{},title:{},comment_num:{}", csuEntity.getAuthor(), csuEntity.getTitle(), csuEntity.getCommentNum());
                }
            }
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
