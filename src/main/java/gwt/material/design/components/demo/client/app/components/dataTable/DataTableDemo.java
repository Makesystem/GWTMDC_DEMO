package gwt.material.design.components.demo.client.app.components.dataTable;

import java.util.Collection;
import java.util.Random;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.ui.MaterialDataTable;
import gwt.material.design.components.client.ui.options.DataTableOptions;
import gwt.material.design.components.client.ui.options.DataTableOptions.Column;

public class DataTableDemo extends Composite {
	
	private static DemoUiBinder uiBinder = GWT.create(DemoUiBinder.class);
	
	interface DemoUiBinder extends UiBinder<Widget, DataTableDemo> {
	}
	
	static final String[] column_0 = { "João Appolinário", "Gilberto Guitti", "Daniel Hoory", "Artur Roberto",
			"Andrea Freire", "Sérgio Almeida", "Eliezer Pazzine", "Jaqueline Bassani", "Richeli Vargas", };
	static final String[] column_1 = { "14_Triplo Diamante Crown", "13_Triplo Diamante Imperial",
			"12_Triplo Diamante Royal", "11_Triplo Diamante Red", "10_Triplo Diamante Black", "09_Triplo Diamante",
			"08_Duplo diamante", "07_Diamante", "06_Esmeralda Plus", "05_Esmeralda", "04_Rubi Plus", "03_Rubi",
			"02_Elite Plus", "01_Elite", "00_Empreendedor", };
	static final double[] column_2 = { 800000.00, 500000.00, 300000.00, 125000.00, 100000.00, 80000.00, 40000.00,
			25000.00, 12000.00, 6000.00, 5000.00, 2000.00, 1000.00, 600.00, 100.00, };
	static final int[] column_3 = { 8, 7, 6, 5, 4, 3, 2, 1, 0 };
	static final String[] column_4 = { "RS", "RJ", "SC", };
	
	@UiField
	MaterialDataTable<Empreendedor> dataTable;
	
	public DataTableDemo() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	protected void onLoad() {
		super.onLoad();
		
		final boolean withRender = true;
		final int rows = 10;
		final int columns = 5;
		final Random random = new Random();
		
		dataTable.addSelectionHandler(event -> print(event.getValue()));
		dataTable.addUnselectionHandler(event -> print(event.getValue()));
		// dataTable.addSelectionHandler(event ->
		// Arrays.stream(event.getValue()).forEach(item -> GWT.log("Select" + item)));
		
		// setStringData(dataTable, withRender, rows, columns, random);
		setObjectData(dataTable, withRender, rows, columns, random);
	}
	
	void print(final Collection<Empreendedor> values) {
		if (values != null)
			for (Empreendedor e : values)
				if (e != null)
					GWT.log("" + e.getNome() + "|" + e.getNivel() + "|" + e.getMedia() + "|" + e.getTempo() + "|"
							+ e.getEstado());
	}
	
	
	void setObjectData(final MaterialDataTable<Empreendedor> dataTable, final boolean withRender, final int rows,
			final int columns, final Random random) {
		
		final Column<Empreendedor> column__name = new Column<>();
		column__name.setTitle("Nome");
		column__name.setWidth("calc(100% - 468px)");
		
		final Column<Empreendedor> column__nivel = new Column<>();
		column__nivel.setTitle("Nível");
		column__nivel.setWidth("188px");
		
		final Column<Empreendedor> column__media = new Column<>();
		column__media.setTitle("Media");
		column__media.setWidth("96px");
		
		final Column<Empreendedor> column__tempo = new Column<>();
		column__tempo.setTitle("Tempo");
		column__tempo.setWidth("68px");
		
		final Column<Empreendedor> column__estado = new Column<>();
		column__estado.setTitle("Estado");
		column__estado.setWidth("68px");
		
		if (withRender) {
			column__name.setRender((data, type, row_data, row, column) -> row_data.getNome());
			column__nivel.setRender((data, type, row_data, row, column) -> {
				switch (type) {
					case SORT:
						return row_data.getNivel();
					default:
						return row_data.getNivel().substring(3);
				}
			});
			column__media.setRender((data, type, row_data, row, column) -> row_data.getMedia());
			column__tempo.setRender((data, type, row_data, row, column) -> row_data.getTempo());
			column__estado.setRender((data, type, row_data, row, column) -> row_data.getEstado());
		}
		
		final DataTableOptions options = new DataTableOptions();
		options.setColumns(column__name, column__nivel, column__media, column__tempo, column__estado);
		
		dataTable.setOptions(options);
				/*
		TimerHelper.schedule(100, () -> {
			
			final long start = System.currentTimeMillis();
			
			final Empreendedor[] data = new Empreendedor[rows];
			
			for (int r = 0; r < rows; r++) {
				data[r] = new Empreendedor(column_0[random.nextInt(column_0.length)],
						column_1[random.nextInt(column_1.length)], column_2[random.nextInt(column_2.length)],
						column_3[random.nextInt(column_3.length)], column_4[random.nextInt(column_4.length)]);
			}
			
			final long end = System.currentTimeMillis();
			
			final long start_2 = System.currentTimeMillis();
			
			dataTable.setData(data);
			
			final long end_2 = System.currentTimeMillis();
			
			Console.log("Create objects: " + (end - start) + "ms");
			Console.log("Draw objects: " + (end_2 - start_2) + "ms");
		});
		*/
	}
	
	public class Empreendedor {
		
		private String nome;
		private String nivel;
		private double media;
		private int tempo;
		private String estado;
		
		public Empreendedor() {
			super();
		}
		
		public Empreendedor(String nome, String nivel, double media, int tempo, String estado) {
			super();
			this.nome = nome;
			this.nivel = nivel;
			this.media = media;
			this.tempo = tempo;
			this.estado = estado;
		}
		
		public String getNome() {
			return nome;
		}
		
		public void setNome(String nome) {
			this.nome = nome;
		}
		
		public String getNivel() {
			return nivel;
		}
		
		public void setNivel(String nivel) {
			this.nivel = nivel;
		}
		
		public double getMedia() {
			return media;
		}
		
		public void setMedia(double media) {
			this.media = media;
		}
		
		public int getTempo() {
			return tempo;
		}
		
		public void setTempo(int tempo) {
			this.tempo = tempo;
		}
		
		public String getEstado() {
			return estado;
		}
		
		public void setEstado(String estado) {
			this.estado = estado;
		}
		
	}
	
	@UiHandler("add_row__act")
	void addRow(final ClickEvent event) {
		
		final Random random = new Random();
		dataTable.addData(new Empreendedor("_" + column_0[random.nextInt(column_0.length)] + " Dynamic",
				column_1[random.nextInt(column_1.length)], column_2[random.nextInt(column_2.length)],
				column_3[random.nextInt(column_3.length)], column_4[random.nextInt(column_4.length)]));
		
	}
}
