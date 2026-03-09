import entities.Machine;
import entities.Salle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import services.MachineService;
import services.SalleService;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class TestMachine {

    private MachineService machineService;
    private Machine machine;
    private Salle salle;
    private SalleService salleService;

    @Before
    public void setUp() {
        machineService = new MachineService();
        salleService = new SalleService();
        salle = new Salle(); // Exemple de salle

        // Persister la salle avant d'utiliser
        salleService.create(salle);

        machine = new Machine();
        machine.setRef();
        machine.setDateAchat(new Date());
        machine.setSalle(salle);

        // Créer et persister la machine avant chaque test
        machineService.create(machine);
    }

    @After
    public void tearDown() {
        // Supprimer la machine après chaque test si elle existe
        Machine foundMachine = machineService.findById(machine.getId());
        if (foundMachine != null) {
            machineService.delete(foundMachine);
        }

        // Supprimer la salle après chaque test si elle existe
        Salle foundSalle = salleService.findById(salle.getId());
        if (foundSalle != null) {
            salleService.delete(foundSalle);
        }
    }

    @Test
    public void testCreate() {
        machine.getId();
    }

    @Test
    public void testFindById() {
        // 1. First SAVE the machine
        machineService.create(machine);
        // 2. Then find it
        Machine foundMachine = machineService.findById(machine.getId());
        assertNotNull("Machine should be found", foundMachine);
        assertEquals("Found machine should match", machine.getRef(), foundMachine.getRef());
    }

    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    public void testUpdate() {
        // 1. First SAVE the machine
        machineService.create(machine);
        machine.setRef(); // Modifier la référence pour tester la mise à jour
        boolean result = machineService.update(machine);
        assertTrue("Machine should be updated successfully", result);
        Machine updatedMachine = machineService.findById(machine.getId());
        assertEquals("Updated machine ref should match", "MACH-002", updatedMachine.getRef());
    }

    @Test
    public void testDelete() {
        boolean result = machineService.delete(machine);
        assertTrue("Machine should be deleted successfully", result);

        Machine foundMachine = machineService.findById(machine.getId());
        assertNull("Machine should not be found after deletion", foundMachine);
    }

    @Test
    public void testFindBetweenDate() {
        machineService.create(machine);
        List<Machine> machines = machineService.findBetweenDate(
                new Date(System.currentTimeMillis() - 86400000), // Hier
                new Date() // Aujourd'hui
        );
        assertNotNull("Machines list should not be null", machines);
        assertFalse("Machines list should contain at least one machine", machines.isEmpty());
    }
}
