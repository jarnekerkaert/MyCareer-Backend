package Realdolmen.MyCareer.qualities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Map;
import java.util.function.Function;
import static java.util.stream.Collectors.toMap;
import java.util.stream.Stream;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import org.hibernate.HibernateException;
import org.hibernate.annotations.TypeDef;
import org.hibernate.engine.spi.SharedSessionContractImplementor;

public enum QualityType
{
    WEAK, STRONG;
}
