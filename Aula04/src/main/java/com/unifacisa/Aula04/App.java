package com.unifacisa.Aula04;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class App 
{
	public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        List<Pessoa> pessoas = getPessoas();

        // Serialize and print to console
        String jsonString = mapper.writeValueAsString(pessoas);
        System.out.println(jsonString);

        // Deserialize
        String jsonInput = "[{\"id\":1,\"nome\":\"Lucas\"},{\"id\":2,\"nome\":\"Maria\"},{\"id\":3,\"nome\":\"Jose\"}]";
        List<Pessoa> p = mapper.readValue(jsonInput, new TypeReference<List<Pessoa>>() {});
        System.out.println("Pessoa: " + p);


        // Save data to JSON file
        try {
            FileWriter writer = new FileWriter("pessoas.json");
            mapper.writeValue(writer, pessoas);
            writer.close();
            System.out.println("Dados salvos em pessoas.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Pessoa> getPessoas() {
        List<Pessoa> pessoas = new ArrayList<Pessoa>();

        Pessoa p1 = new Pessoa();
        p1.setId(1);
        p1.setNome("Lucas");
        p1.setIdade(25);
        p1.setSexo("Masculino");
        p1.setProfissao("Engenheiro");

        Pessoa p2 = new Pessoa();
        p2.setId(2);
        p2.setNome("Maria");
        p2.setIdade(30);
        p2.setSexo("Feminino");
        p2.setProfissao("Médica");
        
        Pessoa p3 = new Pessoa();
        p3.setId(3);
        p3.setNome("Pedro");
        p3.setIdade(21);
        p3.setSexo("Masculino");
        p3.setProfissao("Desenvolvedor");

        pessoas.add(p1);
        pessoas.add(p2);
        pessoas.add(p3);

        return pessoas;
    }
}
