package com.computadores.dal;

import com.computadores.error.DatabaseException;
import com.computadores.model.Cidade;
import com.computadores.model.Cliente;
import com.computadores.model.Endereco;
import com.computadores.model.Estado;
import com.computadores.model.PessoaFisica;
import com.computadores.model.PessoaJuridica;
import com.computadores.model.TipoPessoa;
import com.computadores.util.Hash;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author eduardo
 */
public class ClienteDAOTest {

    private Cliente pf, pj;

    public ClienteDAOTest() {
        // Criando Cliente Pessoa Física
        pf = new PessoaFisica();

        pf.setTipo(TipoPessoa.FISICA);
        ((PessoaFisica) pf).setCpf(2234234407L);
        ((PessoaFisica) pf).setRg(3984545);
        ((PessoaFisica) pf).setNome("Eduardo Macedo Santos");
        try {
            ((PessoaFisica) pf).setDtNasc(new SimpleDateFormat("ddMMyyyy").parse("06101991"));
        } catch (ParseException ex) {
            Logger.getLogger(ClienteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        pf.setTelresidencial("(62) 3454-5555");
        pf.setEmail("dudmacedo@gmail.com");
        pf.setSenha(Hash.getHash(Hash.getHash("8sdff08asud", Hash.MD5), Hash.BCRYPT));
        pf.setAdministrador(false);
        
        ArrayList<Endereco> enderecos = new ArrayList<>();
        
        Endereco end = new Endereco();
        
        end.setCep(74610090);
        end.setLogradouro("Rua 225");
        end.setComplemento("Nº 555");
        end.setBairro("Setor Leste Universitário");
        end.setCidade(new Cidade(2174));
        end.setPadrao(false);
        
        enderecos.add(end);
        
        end = new Endereco();
        
        end.setCep(74785472);
        end.setLogradouro("Rua SR 31");
        end.setComplemento("Quadra 41 Lote 17");
        end.setBairro("Setor Recanto das Minas Gerais");
        end.setCidade(new Cidade(2174));
        end.setPadrao(true);
        
        enderecos.add(end);
        
        pf.setEnderecos(enderecos);
        
        // Criando Cliente Pessoa Jurídica
        pj = new PessoaJuridica();

        pj.setTipo(TipoPessoa.JURIDICA);
        ((PessoaJuridica) pj).setCnpj(2234234407L);
        ((PessoaJuridica) pj).setRazaoSocial("InvictosNet Lan House");

        pj.setTelcomercial("(62) 3454-5555");
        pj.setEmail("invictos@gmail.com");
        pj.setSenha(Hash.getHash(Hash.getHash("8sdff08asud", Hash.MD5), Hash.BCRYPT));
        pj.setAdministrador(false);

    }

    /**
     * TC01 - Método Create e Delete - PF
     *
     * @throws java.lang.Exception
     */
    @Test
    public void TC01() throws Exception {
        ClienteDAO dao = new ClienteDAO();
        dao.create(pf);

        if (pf.getCodigo() <= 0) {
            fail("Houve erro ao tentar inserir registro.");
        }

        dao.delete(pf);
    }

    /**
     * TC02 - Método Create e Delete - PJ
     *
     * @throws java.lang.Exception
     */
    @Test
    public void TC02() throws Exception {
        ClienteDAO dao = new ClienteDAO();
        dao.create(pj);

        if (pj.getCodigo() <= 0) {
            fail("Houve erro ao tentar inserir registro.");
        }

        dao.delete(pj);
    }
    
    /**
     * Test of retrieve method, of class ClienteDAO.
     */
//    @Test
//    public void testRetrieve() throws Exception {
//        System.out.println("retrieve");
//        Object key = null;
//        ClienteDAO instance = new ClienteDAO();
//        Cliente expResult = null;
//        Cliente result = instance.retrieve(key);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of update method, of class ClienteDAO.
     */
//    @Test
//    public void testUpdate() throws Exception {
//        System.out.println("update");
//        Cliente obj = null;
//        ClienteDAO instance = new ClienteDAO();
//        instance.update(obj);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of delete method, of class ClienteDAO.
     */
//    @Test
//    public void testDelete() throws Exception {
//        System.out.println("delete");
//        Cliente obj = null;
//        ClienteDAO instance = new ClienteDAO();
//        instance.delete(obj);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    /**
     * Test of getAll method, of class ClienteDAO.
     */
//    @Test
//    public void testGetAll() throws Exception {
//        System.out.println("getAll");
//        ClienteDAO instance = new ClienteDAO();
//        List<Cliente> expResult = null;
//        List<Cliente> result = instance.getAll();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
