package controller;

import model.Region;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.RegionService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static prototype.RegionPrototype.regionPrototype;

@ExtendWith(MockitoExtension.class)
class RegionControllerTest {

    @InjectMocks
    RegionController regionController;

    @Mock
    RegionService regionService;

    final Long REGION_ID_PROTOTYPE = 666L;

    @Captor
    ArgumentCaptor<Region> requestCaptorRegion = ArgumentCaptor.forClass(Region.class);


    @BeforeEach
    void setUp() {

    }

    @Test
    void getAllRegions() {
        List<Region> list = new ArrayList<>();
        list.add(0, regionPrototype());
        when(regionController.getAllRegions()).thenReturn(list);
        assertNotNull(regionController.getAllRegions().get(0));
        verify(regionService, times(1)).getAllRegions();
    }

    @Test
    void getByIdRegion() {
        when(regionController.getByIdRegion(anyLong())).thenReturn(regionPrototype());
        Region region = regionController.getByIdRegion(REGION_ID_PROTOTYPE);
        assertNotNull(region);
        assertEquals(REGION_ID_PROTOTYPE, region.getId());
        assertEquals(regionPrototype().getName(), region.getName());
        verify(regionService, times(1)).getRegion(anyLong());
    }

    @Test
    void deleteByIdRegion() {
        when(regionController.deleteByIdRegion(anyLong())).thenReturn(true);
        boolean b = regionController.deleteByIdRegion(REGION_ID_PROTOTYPE);
        assertTrue(b);
        verify(regionService, times(1)).deleteRegion(anyLong());
    }

    @Test
    void saveRegion() {
        when(regionController.saveRegion(REGION_ID_PROTOTYPE, any())).thenReturn(regionPrototype());
        Region region = new Region(123l, "D");
        region = regionController.saveRegion(REGION_ID_PROTOTYPE,regionPrototype().getName());
        assertNotEquals("D", region.getName());
        verify(regionService, times(1)).saveRegion(any());
    }

    @Test
    void updateRegion() {
        when(regionController.updateRegion(REGION_ID_PROTOTYPE, any())).thenReturn(regionPrototype());
        Region region = new Region(123l, "D");
        region = regionController.updateRegion(REGION_ID_PROTOTYPE,regionPrototype().getName());
        assertNotEquals("D", region.getName());
        verify(regionService, times(1)).updateRegion(any());

    }


}
