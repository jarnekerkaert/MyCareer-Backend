/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Realdolmen.MyCareer.common;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

/**
 *
 * @author NWTBN85
 */
public class PostgreSQLEnumType extends org.hibernate.type.EnumType {
     @Override
     public void nullSafeSet(
            PreparedStatement st, 
            Object value, 
            int index, 
            SharedSessionContractImplementor session) 
        throws HibernateException, SQLException {
        if(value == null) {
            st.setNull( index, Types.OTHER );
        }
        else {
            st.setObject( 
                index, 
                value.toString(), 
                Types.OTHER 
            );
        }
    }
}
