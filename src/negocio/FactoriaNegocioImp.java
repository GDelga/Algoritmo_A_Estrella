package negocio;

public class FactoriaNegocioImp extends FactoriaNegocio{

	@Override
	public Busqueda crearBusqueda() {
		return new BusquedaImp();
	}

}
