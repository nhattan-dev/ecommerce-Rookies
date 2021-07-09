package com.nhattan.ecommerce.generator;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Stream;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class OrderCodeGenerator implements IdentifierGenerator {
	private final static String prefix = "OC";
	private final static String COUNT_QUERY = "SELECT SUBSTRING(ISNULL(MAX(orderCode), 'OR000000000'), 3, 11) FROM Orders";

	@Override
	public Serializable generate(SessionImplementor session, Object object) throws HibernateException {
		Connection connection = session.connection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(COUNT_QUERY);
			if (rs.next()) {
				int max = rs.getInt(1) + 1;
				return prefix + (String.format("%09", max + 1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
