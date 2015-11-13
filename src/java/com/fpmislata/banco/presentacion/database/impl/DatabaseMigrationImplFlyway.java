package com.fpmislata.banco.presentacion.database.impl;

import com.fpmislata.banco.persistencia.dao.impl.jdbc.DataSourceFactory;
import com.fpmislata.banco.presentacion.database.DatabaseMigration;
import javax.sql.DataSource;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;

public class DatabaseMigrationImplFlyway implements DatabaseMigration {

    @Autowired
    DataSourceFactory dataSourceFactory;

    @Override
    public void migrate() {
        Flyway flyway = new Flyway();
        DataSource dataSource = dataSourceFactory.getDataSource();
        flyway.setDataSource(dataSource);
        flyway.setLocations("com.fpmislata.banco.persistencia.database");
        flyway.setEncoding("UTF-8");

        flyway.migrate();
    }

}
