package clasesATestear.dummies;

import dominio.Prenda;
import dominio.enumsAMockear.Categoria;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PrendaDummy {
    public static Prenda crearMock(Categoria categoria) {
        Prenda prenda = mock(Prenda.class);
        when(prenda.getUnidadCalor()).thenReturn(1);

        return prenda;
    }

    public static Prenda crearMock() {
        return PrendaDummy.crearMock(Categoria.SUPERIOR);
    }
}
