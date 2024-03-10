
package matteroverdrive.handler;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Level;

import matteroverdrive.container.ContainerAnalyzer;
import matteroverdrive.container.ContainerAndroidSpawner;
import matteroverdrive.container.ContainerAndroidStation;
import matteroverdrive.container.ContainerDimensionalPylon;
import matteroverdrive.container.ContainerFactory;
import matteroverdrive.container.ContainerFusionReactor;
import matteroverdrive.container.ContainerInscriber;
import matteroverdrive.container.ContainerReplicator;
import matteroverdrive.container.ContainerSolarPanel;
import matteroverdrive.container.ContainerStarMap;
import matteroverdrive.container.ContainerWeaponStation;
import matteroverdrive.container.MOBaseContainer;
import matteroverdrive.container.matter_network.ContainerPatternMonitor;
import matteroverdrive.gui.GuiAndroidSpawner;
import matteroverdrive.gui.GuiAndroidStation;
import matteroverdrive.gui.GuiChargingStation;
import matteroverdrive.gui.GuiContractMarket;
import matteroverdrive.gui.GuiDecomposer;
import matteroverdrive.gui.GuiDimensionalPylon;
import matteroverdrive.gui.GuiFusionReactor;
import matteroverdrive.gui.GuiHoloSign;
import matteroverdrive.gui.GuiInscriber;
import matteroverdrive.gui.GuiMatterAnalyzer;
import matteroverdrive.gui.GuiMicrowave;
import matteroverdrive.gui.GuiNetworkRouter;
import matteroverdrive.gui.GuiNetworkSwitch;
import matteroverdrive.gui.GuiPatternMonitor;
import matteroverdrive.gui.GuiPatternStorage;
import matteroverdrive.gui.GuiRecycler;
import matteroverdrive.gui.GuiReplicator;
import matteroverdrive.gui.GuiSolarPanel;
import matteroverdrive.gui.GuiSpacetimeAccelerator;
import matteroverdrive.gui.GuiStarMap;
import matteroverdrive.gui.GuiTransporter;
import matteroverdrive.gui.GuiWeaponStation;
import matteroverdrive.gui.MOGuiBase;
import matteroverdrive.gui.MOGuiMachine;
import matteroverdrive.machines.MOTileEntityMachine;
import matteroverdrive.machines.analyzer.TileEntityMachineMatterAnalyzer;
import matteroverdrive.machines.decomposer.TileEntityMachineDecomposer;
import matteroverdrive.machines.dimensional_pylon.TileEntityMachineDimensionalPylon;
import matteroverdrive.machines.fusionReactorController.TileEntityMachineFusionReactorController;
import matteroverdrive.machines.pattern_monitor.TileEntityMachinePatternMonitor;
import matteroverdrive.machines.pattern_storage.TileEntityMachinePatternStorage;
import matteroverdrive.machines.replicator.TileEntityMachineReplicator;
import matteroverdrive.machines.transporter.TileEntityMachineTransporter;
import matteroverdrive.tile.MOTileEntity;
import matteroverdrive.tile.TileEntityAndroidSpawner;
import matteroverdrive.tile.TileEntityAndroidStation;
import matteroverdrive.tile.TileEntityHoloSign;
import matteroverdrive.tile.TileEntityInscriber;
import matteroverdrive.tile.TileEntityMachineChargingStation;
import matteroverdrive.tile.TileEntityMachineContractMarket;
import matteroverdrive.tile.TileEntityMachineMatterRecycler;
import matteroverdrive.tile.TileEntityMachineNetworkRouter;
import matteroverdrive.tile.TileEntityMachineNetworkSwitch;
import matteroverdrive.tile.TileEntityMachineSolarPanel;
import matteroverdrive.tile.TileEntityMachineSpacetimeAccelerator;
import matteroverdrive.tile.TileEntityMachineStarMap;
import matteroverdrive.tile.TileEntityMicrowave;
import matteroverdrive.tile.TileEntityWeaponStation;
import matteroverdrive.util.MOLog;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;

public class GuiHandler implements IGuiHandler {
	private final Map<Class<? extends MOTileEntity>, Class<? extends MOGuiBase>> tileEntityGuiList;
	private final Map<Class<? extends MOTileEntity>, Class<? extends MOBaseContainer>> tileEntityContainerList;

	public GuiHandler() {
		tileEntityGuiList = new HashMap<>();
		tileEntityContainerList = new HashMap<>();
	}

	public void register(Side side) {
		if (side == Side.SERVER) {
			// Container Registration
			registerContainer(TileEntityMachineSolarPanel.class, ContainerSolarPanel.class);
			registerContainer(TileEntityWeaponStation.class, ContainerWeaponStation.class);
			registerContainer(TileEntityMachineFusionReactorController.class, ContainerFusionReactor.class);
			registerContainer(TileEntityAndroidStation.class, ContainerAndroidStation.class);
			registerContainer(TileEntityMachineStarMap.class, ContainerStarMap.class);
			registerContainer(TileEntityInscriber.class, ContainerInscriber.class);
			registerContainer(TileEntityAndroidSpawner.class, ContainerAndroidSpawner.class);
			registerContainer(TileEntityMachineReplicator.class, ContainerReplicator.class);
			registerContainer(TileEntityMachinePatternMonitor.class, ContainerPatternMonitor.class);
			registerContainer(TileEntityMachineMatterAnalyzer.class, ContainerAnalyzer.class);
			registerContainer(TileEntityMachineDimensionalPylon.class, ContainerDimensionalPylon.class);
		} else {
			// Gui Registration
			registerGuiAndContainer(TileEntityMachineReplicator.class, GuiReplicator.class, ContainerReplicator.class);
			registerGui(TileEntityMachineDecomposer.class, GuiDecomposer.class);
			registerGui(TileEntityMachineNetworkRouter.class, GuiNetworkRouter.class);
			registerGuiAndContainer(TileEntityMachineMatterAnalyzer.class, GuiMatterAnalyzer.class,
					ContainerAnalyzer.class);
			registerGui(TileEntityMachinePatternStorage.class, GuiPatternStorage.class);
			registerGuiAndContainer(TileEntityMachineSolarPanel.class, GuiSolarPanel.class, ContainerSolarPanel.class);
			registerGuiAndContainer(TileEntityWeaponStation.class, GuiWeaponStation.class,
					ContainerWeaponStation.class);
			registerGuiAndContainer(TileEntityMachinePatternMonitor.class, GuiPatternMonitor.class,
					ContainerPatternMonitor.class);
			registerGui(TileEntityMachineNetworkSwitch.class, GuiNetworkSwitch.class);
			registerGui(TileEntityMachineTransporter.class, GuiTransporter.class);
			registerGui(TileEntityMachineMatterRecycler.class, GuiRecycler.class);
			registerGuiAndContainer(TileEntityMachineFusionReactorController.class, GuiFusionReactor.class,
					ContainerFusionReactor.class);
			registerGuiAndContainer(TileEntityAndroidStation.class, GuiAndroidStation.class,
					ContainerAndroidStation.class);
			registerGuiAndContainer(TileEntityMachineStarMap.class, GuiStarMap.class, ContainerStarMap.class);
			registerGui(TileEntityHoloSign.class, GuiHoloSign.class);
			registerGui(TileEntityMachineChargingStation.class, GuiChargingStation.class);
			registerGuiAndContainer(TileEntityInscriber.class, GuiInscriber.class, ContainerInscriber.class);
			registerGui(TileEntityMachineContractMarket.class, GuiContractMarket.class);
			registerGuiAndContainer(TileEntityAndroidSpawner.class, GuiAndroidSpawner.class,
					ContainerAndroidSpawner.class);
			registerGui(TileEntityMachineSpacetimeAccelerator.class, GuiSpacetimeAccelerator.class);
			registerGuiAndContainer(TileEntityMachineDimensionalPylon.class, GuiDimensionalPylon.class,
					ContainerDimensionalPylon.class);
			registerGui(TileEntityMicrowave.class, GuiMicrowave.class);
		}
	}

	public void registerContainer(Class<? extends MOTileEntity> tileEntity,
			Class<? extends MOBaseContainer> container) {
		tileEntityContainerList.put(tileEntity, container);
	}

	public void registerGuiAndContainer(Class<? extends MOTileEntity> tileEntity, Class<? extends MOGuiBase> gui,
			Class<? extends MOBaseContainer> container) {
		tileEntityContainerList.put(tileEntity, container);
		tileEntityGuiList.put(tileEntity, gui);
	}

	public void registerGui(Class<? extends MOTileEntity> tileEntity, Class<? extends MOGuiBase> gui) {
		tileEntityGuiList.put(tileEntity, gui);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
        if (entity != null && tileEntityContainerList.containsKey(entity.getClass())) {
            try {
                Class<? extends MOBaseContainer> containerClass = tileEntityContainerList.get(entity.getClass());
                Constructor[] constructors = containerClass.getDeclaredConstructors();
                for (Constructor<?> constructor : constructors) {
                    Class[] parameterTypes = constructor.getParameterTypes();
                    if (parameterTypes.length == 2) {
                        if (parameterTypes[0].isInstance(player.inventory)
                                && parameterTypes[1].isInstance(entity)) {
                            onContainerOpen(entity, Side.SERVER);
                            return constructor.newInstance(player.inventory, entity);
                        }
                    }
                }
            } catch (InvocationTargetException e) {
                MOLog.log(Level.WARN, e, "Could not call TileEntity constructor in server GUI handler");
            } catch (InstantiationException e) {
                MOLog.log(Level.WARN, e, "Could not instantiate TileEntity in server GUI handler");
            } catch (IllegalAccessException e) {
                MOLog.log(Level.WARN, e, "Could not access TileEntity constructor in server GUI handler");
            }
        } else if (entity instanceof MOTileEntityMachine) {
            return ContainerFactory.createMachineContainer((MOTileEntityMachine) entity, player.inventory);
        }
        return null;
	}

	private void onContainerOpen(TileEntity entity, Side side) {
		if (entity instanceof MOTileEntityMachine) {
			((MOTileEntityMachine) entity).onContainerOpen(side);
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity entity = world.getTileEntity(new BlockPos(x, y, z));
        if (tileEntityGuiList.containsKey(entity.getClass())) {
            try {
                Class<? extends MOGuiBase> containerClass = tileEntityGuiList.get(entity.getClass());
                Constructor[] constructors = containerClass.getDeclaredConstructors();
                for (Constructor<?> constructor : constructors) {
                    Class[] parameterTypes = constructor.getParameterTypes();
                    if (parameterTypes.length == 2) {
                        if (parameterTypes[0].isInstance(player.inventory)
                                && parameterTypes[1].isInstance(entity)) {
                            onContainerOpen(entity, Side.CLIENT);
                            return constructor.newInstance(player.inventory, entity);
                        }
                    }
                }
            } catch (InvocationTargetException e) {
                MOLog.log(Level.WARN, e, "Could not call TileEntity constructor in client GUI handler");
            } catch (InstantiationException e) {
                MOLog.log(Level.WARN, e, "Could not instantiate the TileEntity in client GUI handler");
            } catch (IllegalAccessException e) {
                MOLog.log(Level.WARN, e, "Could not access TileEntity constructor in client GUI handler");
            }
        } else if (entity instanceof MOTileEntityMachine) {
            return new MOGuiMachine<>(
                    ContainerFactory.createMachineContainer((MOTileEntityMachine) entity, player.inventory),
                    (MOTileEntityMachine) entity);
        }
        return null;
	}
}
