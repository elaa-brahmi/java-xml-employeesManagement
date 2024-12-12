package com.xml.project.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.xml.project.model.generated.*;
import com.xml.project.model.generated.Tache;
import com.xml.project.service.TacheService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.xml.bind.JAXBException;

@Controller
@RequestMapping("/taches")
public class TacheController {

    private final TacheService tacheService;

    public TacheController(TacheService tacheService) {
        this.tacheService = tacheService;
    }

    // Get all tasks
    @GetMapping
    public String getAllTaches(Model model) throws JAXBException {
        List<Tache> tacheList = tacheService.getAllTaches();
        model.addAttribute("taches",tacheList);
        return "taches/list" ;
    }

    @GetMapping("/tache/{id}")
    public String getTacheById(@PathVariable("id") int id,Model model ) throws JAXBException {
        Tache tache = tacheService.getTacheById(id);
        if (tache != null) {
            model.addAttribute(tache);
            return "taches/detail";
        }else {
            model.addAttribute("error","Tache not found !");
            return "taches/list";
        }
    }

    @GetMapping("/new")
    public String showAddTachesForm(Model model){
        model.addAttribute("taches",new Tache());
        return "taches/new";
    }

    @PostMapping
    public String addTache(@ModelAttribute Tache tache, RedirectAttributes redirectAttributes) throws JAXBException {
        if (tacheService.getTacheById(tache.getIdTache()) != null) {
            redirectAttributes.addFlashAttribute("error", "Tache with the same ID already exists");
            return "redirect:/taches";
        }
        tacheService.addTache(tache);
        redirectAttributes.addFlashAttribute("success","Tache added successfully");
        return "redirect:/taches";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable("id") int id , Model model) throws JAXBException {
        Tache tache = tacheService.getTacheById(id);
        if (tache != null){
            model.addAttribute("tache",tache);
            return "taches/edit" ;
        }else {
            model.addAttribute("error","Tache not found !");
            return "redirect:/taches";
        }

    }

    @PostMapping("/{id}")
    public String updateTache (@PathVariable("id") int id , @ModelAttribute Tache updatedTache ,RedirectAttributes redirectAttributes) throws JAXBException {

        Tache tache = tacheService.updateTache(id,updatedTache);
        if (tache != null){
            redirectAttributes.addFlashAttribute("success","Tache updated successfully ! ");
        }else {
            redirectAttributes.addFlashAttribute("error","Tache not found");
        }
        return "redirect:/taches";

    }

    @GetMapping("/{id}/delete")
    public String deleteTache(@PathVariable("id") int id, RedirectAttributes redirectAttributes,Model model) throws JAXBException {
        boolean removed = tacheService.deleteTacheById(id);
        if (removed) {
            redirectAttributes.addFlashAttribute("success", "Tache deleted successfully!");
            return "redirect:/taches";
        } else {
            model.addAttribute("error", "Tache not found");
            return "taches/list";
        }
    }

}
