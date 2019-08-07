package gwt.material.design.components.demo.client.app.components.dataTable;

import java.util.Random;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.ui.MaterialDataTable;
import gwt.material.design.components.client.ui.MaterialDataTable.Column;
import gwt.material.design.components.client.ui.MaterialDataTable.ColumnRender;
import gwt.material.design.components.client.utils.debug.Console;
import gwt.material.design.components.client.utils.helper.TimerHelper;

public class DataTableDemo extends Composite {
	
	private static DemoUiBinder uiBinder = GWT.create(DemoUiBinder.class);
	
	interface DemoUiBinder extends UiBinder<Widget, DataTableDemo> {
	}
	
	static final String[] column_0 = { "João Appolinário", "Gilberto Guitti", "Daniel Hoory", "Artur Roberto",
			"Andrea Freire", "Sérgio Almeida", "Eliezer Pazzine", "Jaqueline Bassani", "Richeli Vargas", };
	static final String[] column_1 = { 
			"14_Triplo Diamante Crown", 
			"13_Triplo Diamante Imperial",
			"12_Triplo Diamante Royal", 
			"11_Triplo Diamante Red", 
			"10_Triplo Diamante Black", 
			"09_Triplo Diamante",
			"08_Duplo diamante", 
			"07_Diamante", 
			"06_Esmeralda Plus", 
			"05_Esmeralda", 
			"04_Rubi Plus", 
			"03_Rubi",			
			"02_Elite Plus", 
			"01_Elite", 
			"00_Empreendedor", };
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
		final int rows = 20;
		final int columns = 5;
		final Random random = new Random();
		
		// setStringData(dataTable, withRender, rows, columns, random);
		setObjectData(dataTable, withRender, rows, columns, random);
	}
	
	@SuppressWarnings("unchecked")
	void setStringData(final MaterialDataTable<String[]> dataTable, final boolean withRender, final int rows,
			final int columns, final Random random) {
		
		if (withRender) {
			dataTable.setColumns(
					column("Nome", 	 "calc(100% - 360px)", (data, type, row_data, row, column) -> data),
					column("Nível",  "128px", 			   (data, type, row_data, row, column) -> data.substring(3)),
					column("Media",  "96px", 			   (data, type, row_data, row, column) -> data),
					column("Tempo",  "68px", 			   (data, type, row_data, row, column) -> data),
					column("Estado", "68px", 			   (data, type, row_data, row, column) -> data));
		} else {
			dataTable.setColumns(
					column("Nome", 	 "calc(100% - 360px)"), 
					column("Nível",  "128px"),
					column("Media",  "96px"), 
					column("Tempo",  "68px"), 
					column("Estado", "68px"));
		}
		
		TimerHelper.schedule(100, () -> {
			
			final long start = System.currentTimeMillis();
			
			final String[][] data = new String[rows][5];
			for (int r = 0; r < rows; r++) {
				final String[] row = {
						
						column_0[random.nextInt(column_0.length)], 
						column_1[random.nextInt(column_1.length)],
						String.valueOf(column_2[random.nextInt(column_2.length)]),
						String.valueOf(column_3[random.nextInt(column_3.length)]),
						column_4[random.nextInt(column_4.length)] };
				
				data[r] = row;
				
			}
			
			final long end = System.currentTimeMillis();			
			final long start_2 = System.currentTimeMillis();
			
			dataTable.setRows(data);
			
			final long end_2 = System.currentTimeMillis();
			
			Console.log("Create objects: " + (end - start) + "ms");
			Console.log("Draw objects: " + (end_2 - start_2) + "ms");
		});
	}
	
	@SuppressWarnings("unchecked")
	void setObjectData(final MaterialDataTable<Empreendedor> dataTable, final boolean withRender, final int rows,
			final int columns, final Random random) {
		
		if (withRender) {
			dataTable.setColumns(
					column("Nome", 	 "calc(100% - 420px)", (data, type, row_data, row, column) -> row_data.getNome()),
					column("Nível",  "188px", 			   (data, type, row_data, row, column) -> {
						switch(type) {
						case SORT:
							return row_data.getNivel();
						default:
							return row_data.getNivel().substring(3);
						}
					}),
					column("Media",  "96px", 			   (data, type, row_data, row, column) -> String.valueOf(row_data.getMedia())),
					column("Tempo",  "68px", 			   (data, type, row_data, row, column) -> String.valueOf(row_data.getTempo())),
					column("Estado", "68px", 			   (data, type, row_data, row, column) -> row_data.getEstado()));
		} else {
			dataTable.setColumns(
					column("Nome", 	 "calc(100% - 420px)"), 
					column("Nível",  "188px"),
					column("Media",  "96px"), 
					column("Tempo",  "68px"), 
					column("Estado", "68px"));
		}
		
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
			
			dataTable.setRows(data);
			
			final long end_2 = System.currentTimeMillis();
			
			Console.log("Create objects: " + (end - start) + "ms");
			Console.log("Draw objects: " + (end_2 - start_2) + "ms");
		});
		
	}
		
	<D> Column<D> column(final String title, final String width) {
		return column(title, width, null);
	}
	
	<D> Column<D> column(final String title, final String width, final ColumnRender<D> render) {
		final Column<D> column = new Column<D>(title, width, null, null, true, true, true, null, render);
		return column;
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
}
