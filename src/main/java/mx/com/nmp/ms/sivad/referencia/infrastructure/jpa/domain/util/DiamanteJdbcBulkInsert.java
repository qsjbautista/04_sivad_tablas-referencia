/*
 *
 * Microservicios - Tablas de Referencia
 *
 * <p><b>Quarksoft Copyrigth © 2016</b></p>
 *
 */
package mx.com.nmp.ms.sivad.referencia.infrastructure.jpa.domain.util;

import mx.com.nmp.ms.sivad.referencia.dominio.modelo.Diamante;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Utileria para generar una sentencia INSERT de SQL.
 *
 * @author <a href="https://wiki.quarksoft.net/display/~cachavez">Carlos Chávez Melena</a>
 */
public class DiamanteJdbcBulkInsert {
    private static final String QUERY_INSERT = "INSERT INTO ";
    private static final String QUERY_VALUES = " VALUES ";

    private static final String COMA = ", ";
    private static final String PAR_LEF = "(";
    private static final String PAR_RIGHT = ")";
    private static final String TYPE_SEP = "'";

    private String tableName;
    private String values;
    private List<String> props;

    /**
     * Constructor.
     */
    public DiamanteJdbcBulkInsert() {
        super();
    }

    /**
     * Especifica la tabla sobre la que se genera el INSERT.
     *
     * @param tableName Nombre de la tabla.
     *
     * @return Este objeto.
     */
    public DiamanteJdbcBulkInsert withTableName(String tableName) {
        this.tableName = tableName;

        return this;
    }

    /**
     * Especifica los valores que seran insertados.
     *
     * @param diamantes Lista de diamantes a insertar.
     * @param props Propiedades de donde se tomara el valor.
     *
     * @return Este objeto.
     */
    public DiamanteJdbcBulkInsert usingValues(List<? extends Diamante> diamantes, String... props) {
        usingProps(props);

        StringBuilder val = new StringBuilder();
        Iterator<? extends Diamante> it = diamantes.iterator();

        while (it.hasNext()) {
            val.append(gerateSingleValue(it.next()));

            if (it.hasNext()) {
                val.append(COMA);
            }
        }

        values = val.toString();

        return this;
    }

    /**
     * Genera el Query apartir de las propiedades establecidas.
     *
     * @return Sentencia INSERT.
     */
    public String generateQuery() {
        validate();

        return QUERY_INSERT + tableName + QUERY_VALUES + values;
    }

    private void validate() {
        Assert.notNull(tableName, "El nombre de la tabla es requerido");
        Assert.hasText(values, "Los valores a insertar son requeridos");
    }

    private DiamanteJdbcBulkInsert usingProps(String... props) {
        Assert.notEmpty(props, "Faltan propiedade a recuperar del diamane");
        this.props = Arrays.asList(props);

        return this;
    }

    private String gerateSingleValue(Diamante diamante) {
        StringBuilder val = new StringBuilder(PAR_LEF);

        BeanWrapper beanWrapper = PropertyAccessorFactory.forBeanPropertyAccess(diamante);
        Iterator<String> iteratorProp = props.iterator();

        while (iteratorProp.hasNext()) {
            val.append(TYPE_SEP);
            val.append(beanWrapper.getPropertyValue(iteratorProp.next()));
            val.append(TYPE_SEP);

            if (iteratorProp.hasNext()) {
                val.append(COMA);
            }
        }

        val.append(PAR_RIGHT);

        return val.toString();
    }
}
