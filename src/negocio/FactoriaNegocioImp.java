package negocio;

/**
 * @author Guillermo Delgado Yepes
 */
public class FactoriaNegocioImp extends FactoriaNegocio{

	@Override
	public Busqueda crearBusqueda() {
		return new BusquedaImp();
	}

}
