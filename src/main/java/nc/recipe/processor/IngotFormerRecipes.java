package nc.recipe.processor;

import static nc.util.FissionHelper.FUEL_FLUID;
import static nc.util.FissionHelper.FUEL_ORE_DICT;
import static nc.util.FissionHelper.ISOTOPE_FLUID;
import static nc.util.FissionHelper.ISOTOPE_ORE_DICT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import nc.init.NCItems;
import nc.recipe.ProcessorRecipeHandler;
import nc.util.FluidStackHelper;
import nc.util.OreDictHelper;
import nc.util.StringHelper;
import net.minecraft.init.Items;
import net.minecraftforge.fluids.FluidRegistry;

public class IngotFormerRecipes extends ProcessorRecipeHandler {
	
	public IngotFormerRecipes() {
		super("ingot_former", 0, 1, 1, 0);
	}
	
	@Override
	public void addRecipes() {
		addIngotFormingRecipes();
		
		addIngotFormingRecipe("hard_carbon", "HardCarbon");
		addIngotFormingRecipe("manganese_dioxide", "ManganeseDioxide");
		addIngotFormingRecipe("lead_platinum", "LeadPlatinum");
		
		addRecipe(fluidStack("bas", FluidStackHelper.GEM_VOLUME), "gemBoronArsenide", 2D, 2D);
		
		if (OreDictHelper.oreExists("ingotObsidian")) {
			addRecipe(fluidStack("obsidian", FluidStackHelper.SEARED_MATERIAL_VOLUME), "ingotObsidian", 0.5D, 2D);
		}
		else {
			addRecipe(fluidStack("obsidian", FluidStackHelper.SEARED_BLOCK_VOLUME), "obsidian", 2D, 2D);
		}
		addRecipe(fluidStack("redstone", FluidStackHelper.REDSTONE_DUST_VOLUME), "ingotRedstone", 0.25D, 1D);
		addRecipe(fluidStack("glowstone", FluidStackHelper.GLOWSTONE_DUST_VOLUME), "ingotGlowstone", 0.25D, 1D);
		addRecipe(fluidStack("coal", FluidStackHelper.COAL_DUST_VOLUME), "ingotGraphite", 0.5D, 1D);
		addRecipe(fluidStack("prismarine", FluidStackHelper.INGOT_VOLUME), "gemPrismarine", 1D, 1D);
		
		if (OreDictHelper.oreExists("ingotSilicon")) addRecipe(fluidStack("silicon", FluidStackHelper.INGOT_VOLUME), "ingotSilicon", 1D, 1D);
		else addRecipe(fluidStack("silicon", FluidStackHelper.INGOT_VOLUME), "itemSilicon", 1D, 1D);
		
		// Tinkers' Construct
		/*addIngotFormingRecipe("Manyullyn");
		addIngotFormingRecipe("Alubrass");
		addIngotFormingRecipe("Pigiron");
		addIngotFormingRecipe("Brass");
		addIngotFormingRecipe("Bronze");
		addIngotFormingRecipe("Electrum");
		addIngotFormingRecipe("Steel");*/
		
		// EnderIO
		addIngotFormingRecipe("electrical_steel", "ElectricalSteel");
		addIngotFormingRecipe("energetic_alloy", "EnergeticAlloy");
		addIngotFormingRecipe("vibrant_alloy", "VibrantAlloy");
		addIngotFormingRecipe("redstone_alloy", "RedstoneAlloy");
		addIngotFormingRecipe("conductive_iron", "ConductiveIron");
		addIngotFormingRecipe("pulsating_iron", "PulsatingIron");
		addIngotFormingRecipe("dark_steel", "DarkSteel");
		addIngotFormingRecipe("soularium", "Soularium");
		addIngotFormingRecipe("end_steel", "EndSteel");
		addIngotFormingRecipe("construction_alloy", "ConstructionAlloy");
		
		// Endergy
		addIngotFormingRecipe("crude_steel", "CrudeSteel");
		addIngotFormingRecipe("crystalline_alloy", "CrystallineAlloy");
		addIngotFormingRecipe("melodic_alloy", "MelodicAlloy");
		addIngotFormingRecipe("stellar_alloy", "StellarAlloy");
		addIngotFormingRecipe("crystalline_pink_slime", "CrystallinePinkSlime");
		addIngotFormingRecipe("energetic_silver", "EnergeticSilver");
		addIngotFormingRecipe("vivid_alloy", "VividAlloy");
		
		// Mekanism
		addRecipe(fluidStack("brine", 15), "dustSalt", 0.25D, 0.5D);
		
		// Sweets
		addRecipe(fluidStack("cocoa_butter", FluidStackHelper.INGOT_VOLUME), "ingotCocoaButter", 0.5D, 0.5D);
		addRecipe(fluidStack("unsweetened_chocolate", FluidStackHelper.INGOT_VOLUME), "ingotUnsweetenedChocolate", 0.5D, 0.5D);
		addRecipe(fluidStack("dark_chocolate", FluidStackHelper.INGOT_VOLUME), "ingotDarkChocolate", 0.5D, 0.5D);
		addRecipe(fluidStack("milk_chocolate", FluidStackHelper.INGOT_VOLUME), "ingotChocolate", 0.5D, 0.5D);
		addRecipe(fluidStack("sugar", FluidStackHelper.INGOT_VOLUME), Items.SUGAR, 0.5D, 0.5D);
		addRecipe(fluidStack("gelatin", FluidStackHelper.INGOT_VOLUME), NCItems.gelatin, 0.5D, 0.5D);
		addRecipe(fluidStack("marshmallow", FluidStackHelper.INGOT_VOLUME), "ingotMarshmallow", 0.5D, 0.5D);
		
		// Fission Isotopes
		addIsotopeFormingRecipes();
		
		// Fission Fuels
		addFissionFuelFormingRecipes();
	}
	
	public void addIngotFormingRecipe(String fluid, String metal) {
		addRecipe(fluidStack(fluid.toLowerCase(Locale.ROOT), FluidStackHelper.INGOT_VOLUME), "ingot" + metal, 1D, 1D);
	}
	
	public void addIngotFormingRecipe(String metal) {
		addIngotFormingRecipe(metal, metal);
	}
	
	public void addIsotopeFormingRecipes() {
		for (int i = 0; i < ISOTOPE_ORE_DICT.length; i++) {
			addIngotFormingRecipe(ISOTOPE_FLUID[i], ISOTOPE_ORE_DICT[i]);
			addIngotFormingRecipe(ISOTOPE_FLUID[i] + "_za", ISOTOPE_ORE_DICT[i] + "ZA");
		}
	}
	
	public void addFissionFuelFormingRecipes() {
		for (int i = 0; i < FUEL_ORE_DICT.length; i++) {
			addRecipe(fluidStack("fuel_" + FUEL_FLUID[i], FluidStackHelper.INGOT_VOLUME), "fuel" + FUEL_ORE_DICT[i], 1D, 1D);
			addRecipe(fluidStack("depleted_fuel_" + FUEL_FLUID[i], FluidStackHelper.INGOT_VOLUME), "depletedFuel" + FUEL_ORE_DICT[i], 1D, 1D);
		}
	}
	
	private static final List<String> CASTING_BLACKLIST = Arrays.asList("glass", "coal", "redstone", "glowstone", "prismarine", "obsidian", "silicon", "marshmallow");
	
	public void addIngotFormingRecipes() {
		ArrayList<String> fluidList = new ArrayList(FluidRegistry.getRegisteredFluids().keySet());
		for (String fluidName : fluidList) {
			if (CASTING_BLACKLIST.contains(fluidName)) continue;
			String materialName = StringHelper.capitalize(fluidName);
			String ingot = "ingot" + materialName;
			String gem = "gem" + materialName;
			if (OreDictHelper.oreExists(ingot)) addRecipe(fluidStack(fluidName, FluidStackHelper.INGOT_VOLUME), ingot, 1D, 1D);
			else if (OreDictHelper.oreExists(gem)) addRecipe(fluidStack(fluidName, FluidStackHelper.GEM_VOLUME), gem, 1D, 1D);
		}
	}
}
