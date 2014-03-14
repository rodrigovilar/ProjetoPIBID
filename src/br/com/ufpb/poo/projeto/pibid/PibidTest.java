package br.com.ufpb.poo.projeto.pibid;
import static org.junit.Assert.*;
import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class PibidTest {
	Pibid pibid;
	
	@Before
	public void Inicializar() {
		this.pibid = new Pibid();
	}
	
	@Test(expected = CoordenadorExistenteException.class)
	public void testarCadastroCoordenadorDuplicadoTest() {
		Coordenador coord = new Coordenador("Ana Liz","12345678");
		Coordenador coord2 = new Coordenador("Ana Liz","12345678");
		pibid.cadastraCoordenador(coord);
		pibid.cadastraCoordenador(coord2);
		
	}
	
	@Test
	public void CadastrarCoordenadorTest(){
		Coordenador coord = new Coordenador("Ana Liz","12345678");
		pibid.cadastraCoordenador(coord);
	}
	
	
	@Test
	public void cadastraAlunoTest(){
		Aluno aluno= new Aluno("Luana Costa","81211021");
		pibid.cadastrarAluno(aluno);
		List<Aluno> list=pibid.getListaDeAlunosCriados();
		assertEquals(1,list.size());
		assertEquals(aluno,list.get(0));
	}
	
	@Test(expected = AlunoExistenteException.class)
	public void cadastrarAlunoDuplicadoTest(){
		Aluno aluno1= new Aluno("Luana Tain�","81211021");
		Aluno aluno2= new Aluno("Luana Tain�","81211021");
		pibid.cadastrarAluno(aluno1);
		pibid.cadastrarAluno(aluno2);
	}
	
	@Test
	public void cadastrarTarefasTest(){
		Tarefa tarefa=new Tarefa("Ministrar aula de HTML");
		Grupo g = new Grupo(tarefa,"01");
		pibid.cadastrarTarefa(tarefa);
		pibid.cadastrarGrupo(g);
		List<Tarefa> lista=pibid.getListaDeTarefas();
		assertEquals(1,lista.size());
		assertEquals(tarefa,lista.get(0));
	}
	
	@Test 
	public void cadastrarAlunosEmGrupoTest(){
		Tarefa t = new Tarefa("Ministrar curso de Scratch");
		Grupo g = new Grupo(t,"123");
		pibid.cadastrarGrupo(g);
		Aluno a = new Aluno("Deyvison","12234576");
		Aluno a1 = new Aluno("Tayna","53344545");
		pibid.cadastrarParticipante(a,g);
		pibid.cadastrarParticipante(a1,g);
		List<Aluno> participantes = g.listParticipantes();
		assertEquals(2, participantes.size());
		assertEquals(a, participantes.get(0));
		assertEquals(a1, participantes.get(1));	
	}
	
	@Test
	public void cadastrarGrupoTest(){
		Tarefa tarefa = new Tarefa("Ministrar Aula de Python");
		Grupo grupo = new Grupo(tarefa,"012");
		pibid.cadastrarGrupo(grupo);
		assertEquals(grupo,pibid.pesquisarGrupo("012"));
	}
	
	@Test
	public void pesquisarGrupoInexistenteTest(){
		Tarefa t = new Tarefa("Ministrar curso de HTML");
		Grupo g = new Grupo(t,"123");
		pibid.cadastrarGrupo(g);
		assertNull(pibid.pesquisarGrupo("321"));
	}
	
	@Test
	public void pesquisarCoordenadorTest(){
		Coordenador c = new Coordenador("Ana Liz", "1241243");
		pibid.cadastraCoordenador(c);
		assertEquals(c, pibid.pesquisarCoordenador("1241243"));
	}
	@Test(expected = CoordenadorInexistenteException.class)
	public void pesquisarCoordenadorInexistenteTest(){
		Coordenador c = new Coordenador("Flavia Veloso", "8124122");
		pibid.cadastraCoordenador(c);
		assertEquals(c,pibid.pesquisarCoordenador("9123124"));
	}
	
	@Test
	public void pesquisaAlunoTest(){
		Aluno a = new Aluno("Luana","81211067");
		pibid.cadastrarAluno(a);
		assertEquals(a,pibid.pesquisaAluno("81211067"));	
	}
	
	@Test(expected = AlunoInexistenteException.class)
	public void pesquisarAlunoInexistenteTest(){
		Aluno a = new Aluno("Marina","81211133");
		pibid.cadastrarAluno(a);
		Aluno a1 = pibid.pesquisaAluno("1312312");
	}
	
	@Test
	public void removerAlunoPelaMatriculaTest(){
		Aluno a= new Aluno("Luana","81211021");
		pibid.cadastrarAluno(a);
		pibid.removerAlunoPelaMatricula("81211021");
		List<Aluno> alunos = pibid.getListaDeAlunosCriados();
		assertEquals(0,alunos.size());	
	}
	
	@Test
	public void removerCoordenadorPeloSiapeTest(){
		Coordenador c= new Coordenador("Ana Liz","12345678");
		pibid.cadastraCoordenador(c);
		pibid.removerCoordenadorPeloSiape("12345678");
		List<Coordenador> coord = pibid.getListaDeCoordenadoresCriados();
		assertEquals(0,coord.size());	
	}
	
	@Test(expected = CoordenadorInexistenteException.class)
	public void removerCoordenadorInexistenteTest(){
		pibid.removerCoordenadorPeloSiape("81241432");
		List<Coordenador> coord = pibid.getListaDeCoordenadoresCriados();
		assertEquals(0,coord.size());
	}
	
	@Test(expected = Exception.class)
	public void cadastraUmAlunoQueJaParticipeDoGrupoTest(){
		Aluno a = new Aluno("Deyvison", "66557788");
		pibid.cadastrarAluno(a);
		Aluno a1 = pibid.pesquisaAluno("66557788");
		Tarefa t = new Tarefa("Planejar aula");
		Grupo g = new Grupo(t,"123");
		pibid.cadastrarParticipante(a1,g);
		pibid.cadastrarParticipante(a1,g);
	}
}
