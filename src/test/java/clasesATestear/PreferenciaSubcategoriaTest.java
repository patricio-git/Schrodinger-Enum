package clasesATestear;

import clasesATestear.dummies.PrendaDummy;
import dominio.Prenda;
import dominio.clasesATestear.PreferenciaSubcategoria;
import dominio.enumsAMockear.Categoria;
import dominio.enumsAMockear.Subcategoria;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class PreferenciaSubcategoriaTest {
    private Categoria categoria;
    private Subcategoria subcategoria;
    private PreferenciaSubcategoria preferenciaSubcategoria;
    private Prenda prenda;
    private List<Prenda> prendas;

    @Before
    public void setUp() {
        this.categoria = mock(Categoria.class);
        this.subcategoria = mock(Subcategoria.class);
        this.preferenciaSubcategoria = new PreferenciaSubcategoria( 0, 10, this.categoria, this.subcategoria);
        this.prenda = PrendaDummy.crearMock();
        this.prendas = new ArrayList<Prenda>() {{
            add(prenda);
        }};
        when(this.subcategoria.obtenerTemperatura(this.prendas)).thenReturn(5.0);
    }

    @Test
    public void cercania() {
        assertEquals(0.0, this.preferenciaSubcategoria.cercania(this.prendas, 0), 0);

        verify(this.subcategoria, times(1)).obtenerTemperatura(this.prendas);
    }

    @Test
    public void cumple() {
        assertTrue(this.preferenciaSubcategoria.cumple(this.prendas, 0));

        verify(this.subcategoria, times(1)).obtenerTemperatura(this.prendas);
    }

    @Test
    public void no_cumple() {
        assertFalse(this.preferenciaSubcategoria.cumple(this.prendas, 15));

        verify(this.subcategoria, times(1)).obtenerTemperatura(this.prendas);
    }

    @Test
    public void ajustar_ambos_margenes_misma_temperatura() {
        this.preferenciaSubcategoria.ajustar(5);

        assertEquals(5, this.preferenciaSubcategoria.getTemperaturaInferior());
        assertEquals(15, this.preferenciaSubcategoria.getTemperaturaSuperior());
    }
}