package gwt.material.design.components.demo.client.app.components.spreadsheet;

public class CsvUtils {

	public static String identifyColumnName(final String columnName)  {

		final String text = columnName.replaceAll("[0-9]", "").toLowerCase().trim();
		final String rawNumber = columnName.replaceAll("[^0-9]", "").trim();
		final String number = rawNumber.equals("1") ? "" : rawNumber;
		
		switch (text) {
		/* DDD */
		case "dd":
		case "valordd":
		case "ddd":
		case "valorddd":
		case "dddd":
		case "valordddd":
			return "ddd" + number;
		/* Telefone */
		case "telefone":
		case "telefonne":
		case "telef":
		case "teleff":
		case "phone":
		case "phonne":
		case "fone":
		case "fonne":
		case "tel":
			return "telefone" + number;
		/* Data de Bloqueio */
		case "databloqueio":
		case "data_bloqueio":
		case "databloquei":
		case "data_bloquei":
		case "databloque":
		case "data_bloque":
		case "databloqu":
		case "data_bloqu":
		case "databloq":
		case "data_bloq":
		case "datainicio":
		case "data_inicio":
		case "apartirde":
		case "apartir_de":
			return "databloqueio" + number;
		/* Evento */
		case "evento":
		case "event":
		case "even":
			return "evento" + number;
		/* Data de inscrição */
		case "datainscricao":
		case "data_inscricao":
		case "datainscrica":
		case "data_inscrica":
		case "datainscric":
		case "data_inscric":
		case "datainscri":
		case "data_inscri":
		case "datainscr":
		case "data_inscr":
		case "datainsc":
		case "data_insc":
		case "datains":
		case "data_ins":
		case "datacadastro":
		case "data_cadastro":
		case "cadastro":
		case "cadastradoem":
		case "cadastrado_em":
			return "datainscricao" + number;
		/* Nome */
		case "nome":
		case "nomee":
		case "name":
		case "namee":
		case "contato":
		case "contatoo":
		case "contat":
		case "contat.":
		case "razaosocial":
		case "razaosociall":
		case "razao_social":
		case "razao_sociall":
		case "nomecliente":
		case "nome_cliente":
		case "nome/razaosocial":
		case "nome_razaosocial":
		case "name/razaosocial":
		case "name_razaosocial":
		case "nome/razao_social":
		case "nome_razao_social":
		case "name/razao_social":
		case "name_razao_social":
		case "razaosocial/nome":
		case "razaosocial/name":
		case "cliente":
		case "client":
		case "client.":
		case "clientee":
			return "nome" + number;
		/* Tipo pessoa */
		case "tipopessoa":
		case "tipo_pessoa":
		case "pessoa":
		case "pessoaa":
		case "pf/pj":
		case "pfpj":
		case "pj/pf":
		case "pjpf":
		case "tipop":
		case "tipope":
		case "tipopes":
		case "tipodepessoa":
		case "tipodepesoa":
		case "tipo_de_pessoa":
			return "tipopessoa" + number;
		/* E-mail */
		case "email":
		case "emaill":
		case "eemail":
		case "eemaill":
		case "e-mail":
		case "e-maill":
		case "mail":
		case "maill":
			return "email" + number;
		/* Documento */
		case "documento":
		case "cpf":
		case "cnpj":
		case "cpf/cnpj":
		case "cpf_cnpj":
		case "cpfcnpj":
		case "cnpj/cpf":
		case "cnpj_cpf":
		case "cnpjcpf":
		case "docume":
		case "docume.":
		case "documen":
		case "documen.":
		case "document":
		case "document.":
		case "doc":
		case "doc.":
			return "documento" + number;
		/* Logradouro */
		case "logradouro":
		case "logradouroo":
		case "lograd":
		case "lograd.":
		case "logrado":
		case "logrado.":
		case "endereco":
		case "endereço":
		case "rua":
		case "ende":
		case "ende.":
		case "end.":
		case "endere":
		case "endere.":
		case "enderee":
		case "enderee.":
		case "enderec":
		case "enderec.":
		case "enderecoo":
			return "logradouro" + number;
		/* Número */
		case "numero":
		case "número":
		case "numeroo":
		case "number":
		case "nr":
		case "nr.":
		case "nro":
		case "nro.":
		case "num":
		case "num.":
		case "nmero":
		case "nmero.":
		case "n":
		case "n.":
		case "n°":
			return "numero" + number;
		/* Complemento */
		case "complemento":
		case "comp":
		case "comp.":
		case "compl":
		case "compl.":
		case "comple":
		case "comple.":
		case "complem":
		case "complem.":
		case "compleme":
		case "compleme.":
		case "complemen":
		case "complemen.":
		case "complement":
		case "complement.":
			return "complemento" + number;
		/* CEP */
		case "cep":
		case "cp":
		case "cp.":
			return "cep" + number;
		/* Bairro */
		case "bairro":
		case "bairr":
		case "bairr.":
		case "bair":
		case "bair.":
			return "bairro" + number;
		/* Cidade */
		case "cidade":
		case "cidadee":
		case "city":
		case "cidad":
		case "cidad.":
		case "cida":
		case "cida.":
			return "cidade" + number;
		/* Estado */
		case "estado":
		case "uf":
		case "estad":
		case "estad.":
			return "estado" + number;
		/* Quadra */
		case "quadra":
		case "qd":
		case "qd.":
		case "quad":
		case "quad.":
		case "quadr":
		case "quadr.":
			return "quadra" + number;
		/* Lote */
		case "lote":
		case "lot":
		case "lot.":
		case "lt":
		case "lt.":
			return "lote" + number;
		/* Bloco */
		case "bloco":
		case "bl":
		case "bl.":
		case "bloc":
		case "bloc.":
			return "bloco" + number;
		default:
			return null;
		}

	}

}
