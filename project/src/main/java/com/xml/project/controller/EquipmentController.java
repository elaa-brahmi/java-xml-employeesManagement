package com.xml.project.controller;



import com.xml.project.model.generated.Equipment;
import com.xml.project.model.generated.Equipments;
import com.xml.project.model.generated.ReusedIdException;
import com.xml.project.service.EquipmentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.xml.bind.JAXBException;
import java.util.List;

@Controller
@RequestMapping("/equipments")
public class EquipmentController {

    private final EquipmentService equipmentService ;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping
    public String getAllEquipments(Model model) throws JAXBException {
        List<Equipment> equipmentList = equipmentService.getAllEquipments();
        model.addAttribute("equipments",equipmentList);
        return "equipments/list";
    }

    @GetMapping("/equipment/{id}")
    public String getEquipmentById(@PathVariable("id") int id,Model model ) throws JAXBException {
        Equipment equipment = equipmentService.getEquipmentById(id);
        if (equipment != null) {
            model.addAttribute(equipment);
            return "equipments/detail";
        }else {
            model.addAttribute("error","Equipment not found !");
            return "equipments/list";
        }
    }

    @GetMapping("/new")
    public String showAddEquipmentForm(Model model){
        model.addAttribute("equipment",new Equipment());
        return "equipments/new";
    }

    @PostMapping
    public String addEquipment(@ModelAttribute Equipment equipment, RedirectAttributes redirectAttributes) throws JAXBException {
        if (equipmentService.getEquipmentById(equipment.getIdEquipment()) != null) {
            redirectAttributes.addFlashAttribute("error", "An equipment with the same ID already exists");
            return "redirect:/equipments/new";
        }
        equipmentService.addEquipment(equipment);
        redirectAttributes.addFlashAttribute("success","Equipment added successfully");
        return "redirect:/equipments";
    }

    @GetMapping("/{id}/edit")
    public String showUpdateForm(@PathVariable("id") int id , Model model) throws JAXBException {
        Equipment equipment = equipmentService.getEquipmentById(id);
        if (equipment != null){
            model.addAttribute("equipment",equipment);
            return "equipments/edit" ;
        }else {
            model.addAttribute("error","Equipment not found !");
            return "redirect:/equipments";
        }

    }

    @PostMapping("/{id}")
    public String updateEquipment (@PathVariable("id") int id , @ModelAttribute Equipment updatedEquipment ,RedirectAttributes redirectAttributes) throws JAXBException {

        Equipment equipment = equipmentService.updateEquipment(id,updatedEquipment);
        if (equipment != null){
            redirectAttributes.addFlashAttribute("success","Equipment updated successfully ! ");
        }else {
            redirectAttributes.addFlashAttribute("error","Employee not found");
        }
        return "redirect:/equipments";

    }

    @GetMapping("/{id}/delete")
    public String deleteEquipment(@PathVariable("id") int id, RedirectAttributes redirectAttributes,Model model) throws JAXBException {
        boolean removed = equipmentService.deleteEquipmentById(id);
        if (removed) {
            redirectAttributes.addFlashAttribute("success", "Equipment deleted successfully!");
            return "redirect:/equipments";
        } else {
            model.addAttribute("error", "Equipment not found");
            return "equipments/list";
        }
    }


}
