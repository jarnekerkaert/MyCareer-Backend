
package com.realdolmen.mycareer.qualities;

import com.realdolmen.mycareer.common.QualityType;
import com.realdolmen.mycareer.common.dto.QualityModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class QualityControllerTest {

    @Mock
    private QualityService serviceMock;
    private QualityController controller;
    private List<Quality> qualities;
    private List<QualityModel> qualityModels;

    @Before
    public void setUp() {
        controller = new QualityController(serviceMock);
        qualities = makeQualities();
        qualityModels = makeQualityModels();
    }

    @Test
    public void getQualitiesOfEmployee() {
        controller.getAllQualitiesEmployee(1L);

        Mockito.verify(serviceMock).findByEmployeeId(1L);
    }

    @Test
    public void updateQualitiesOfEmployee() {
        controller.updateQualities(1L, qualityModels);

        Mockito.verify(serviceMock).deleteByEmployeeId(1L);
        Mockito.verify(serviceMock).saveQualities(ArgumentMatchers.refEq(qualities));
    }

    private List<Quality> makeQualities() {
        Quality quality1 = new Quality();
        quality1.setType(QualityType.WEAK);
        quality1.setDescription("description1");
        quality1.setId(300L);
        quality1.setEmployeeId(1L);

        Quality quality2 = new Quality();
        quality2.setType(QualityType.STRONG);
        quality2.setDescription("description1");
        quality2.setId(301L);
        quality2.setEmployeeId(1L);

        return new ArrayList<>(Arrays.asList(quality1, quality2));
    }

    private List<QualityModel> makeQualityModels() {
        QualityModel quality1 = new QualityModel();
        quality1.setType(QualityType.WEAK);
        quality1.setDescription("description1");
        quality1.setId(300L);
        quality1.setEmployeeId(1L);

        QualityModel quality2 = new QualityModel();
        quality2.setType(QualityType.STRONG);
        quality2.setDescription("description1");
        quality2.setId(301L);
        quality2.setEmployeeId(1L);

        return new ArrayList<>(Arrays.asList(quality1, quality2));
    }
}
