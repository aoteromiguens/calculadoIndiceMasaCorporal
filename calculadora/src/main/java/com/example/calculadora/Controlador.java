package com.example.calculadora;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class Controlador {

    public static float calcularIndice(float altura ,float peso){
        if (altura==0) return 0;
        else return peso/(altura*altura);
    }

    public  static String clasificarIndice(float indice){
        String clasificar = "";
        if (indice >= 30){
            clasificar += ("obesidad");
            if (indice < 35 ) clasificar += (" moderada");
            else{
                if(indice > 40) clasificar += (" mórvida");
                else clasificar += (" severa");
            }
        }
        else{
            if (indice <= 18.4){
                if (indice < 15) clasificar += ("muy ");
                if (indice < 16) clasificar += ("severa ");
                clasificar += ("delgadez");
            }
            else{
            if ( indice < 25) clasificar += ("Peso correcto");
            else clasificar += ("Sobre peso");
            }
        }
        return clasificar;
    }

    public static String declararImagen(float indice){
        String imagen = "img/pregunta.png";
        if (indice == 0) return imagen;
        if (indice >= 30){
            imagen = "img/peligro.jpg";
        }
        else{
            if (indice <= 18.4){
                imagen = "img/cuidado.webp";
                if (indice < 16) imagen = "img/peligro.jpg";
            }
            else{
            if ( indice < 25) imagen = "img/correcto.webp";
            else imagen = "img/cuidado.webp";
            }
        }


        return imagen;
    }




    @GetMapping("/calculadoraIndice")

    public String calculadoraIndice(
        @RequestParam(name="peso", required=true,defaultValue="0") float peso,
        @RequestParam(name="altura", required=true,defaultValue="0") float altura,
        Model model){
        float indice = calcularIndice(altura,peso);
        String clasificar = clasificarIndice(indice);
        String imagen = declararImagen(indice);
        if (indice==0){
            model.addAttribute("indice", "Esperando valores");
            model.addAttribute("clasificar", "Esperando valores");
            model.addAttribute("peso", "75.00");
            model.addAttribute("altura", "1.80");
        }
        else{
            model.addAttribute("indice", indice);
            model.addAttribute("clasificar", clasificar);
            model.addAttribute("peso", peso);
            model.addAttribute("altura", altura);
        }
        model.addAttribute("imagen", imagen);

        
        return "calculadoraIndice";
    }

    
}
