package com.fpmislata.banco.presentacion.controladores;

import com.fpmislata.banco.negocio.dominio.EntidadBancaria;
import com.fpmislata.banco.negocio.servicios.EntidadBancariaService;
import com.fpmislata.banco.presentacion.json.JsonTransformer;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EntidadBancariaController {

    @Autowired
    EntidadBancariaService entidadBancariaService;
    @Autowired
    JsonTransformer jsonTransformer;

    @RequestMapping(value = "/entidadbancaria/{id}", method = RequestMethod.GET, produces = "application/json")
    public void get(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @PathVariable("id") int id) {

        try {
            EntidadBancaria entidadBancaria = entidadBancariaService.get(id);

            String jsonUsuario = jsonTransformer.toJson(entidadBancaria);

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().println(jsonUsuario);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/entidadbancaria", method = RequestMethod.GET, produces = "application/json")
    public void findall(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse) {

        try {
            List<EntidadBancaria> entidadesBancarias = entidadBancariaService.findAll();

            String jsonEntidadesBancarias = jsonTransformer.toJson(entidadesBancarias);

            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().println(jsonEntidadesBancarias);

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/entidadbancaria/{id}", method = RequestMethod.DELETE)
    public void delete(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @PathVariable("id") int id) {
        entidadBancariaService.delete(id);
        httpServletResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @RequestMapping(value = "/entidadbancaria", method = RequestMethod.POST, produces = "application/json")
    public void insert(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada) {

        try {

            EntidadBancaria entidadBancaria = entidadBancariaService.insert(jsonTransformer.fromJSON(jsonEntrada, EntidadBancaria.class));

            String jsonUsuario = jsonTransformer.toJson(entidadBancaria);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().println(jsonUsuario);

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @RequestMapping(value = "/entidadbancaria/{id}", method = RequestMethod.PUT, produces = "application/json")
    public void update(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse, @RequestBody String jsonEntrada, @PathVariable("id") int id) {

        try {

            EntidadBancaria entidadBancaria = entidadBancariaService.update(jsonTransformer.fromJSON(jsonEntrada, EntidadBancaria.class));

            if (entidadBancaria.getIdEntidadBancaria() != id) {
                throw new RuntimeException("Las id eran distintas " + entidadBancaria.getIdEntidadBancaria() + " y " + id);
            }

            String jsonUsuario = jsonTransformer.toJson(entidadBancaria);
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getWriter().println(jsonUsuario);

        } catch (Exception ex) {
            Logger.getLogger(EntidadBancariaController.class.getName()).log(Level.SEVERE, null, ex);
            httpServletResponse.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            httpServletResponse.setContentType("text/plain");
            try {
                ex.printStackTrace(httpServletResponse.getWriter());
            } catch (IOException ex1) {
                Logger.getLogger(EntidadBancariaController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
}
