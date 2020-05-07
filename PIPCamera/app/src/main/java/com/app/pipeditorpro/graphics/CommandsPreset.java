package com.app.pipeditorpro.graphics;

import android.graphics.Color;

import com.app.pipeditorpro.R;
import com.app.pipeditorpro.graphics.commands.BlackFrameCommand;
import com.app.pipeditorpro.graphics.commands.ColorBoostCommand;
import com.app.pipeditorpro.graphics.commands.ColorFilterCommand;
import com.app.pipeditorpro.graphics.commands.DecreaseColorDepthCommand;
import com.app.pipeditorpro.graphics.commands.EmptyCommand;
import com.app.pipeditorpro.graphics.commands.GammaCorrectionCommand;
import com.app.pipeditorpro.graphics.commands.GrayscaleCommand;
import com.app.pipeditorpro.graphics.commands.ImageProcessingCommand;
import com.app.pipeditorpro.graphics.commands.InvertColorCommand;
import com.app.pipeditorpro.graphics.commands.MirrorCommand;
import com.app.pipeditorpro.graphics.commands.SepiaCommand;
import com.app.pipeditorpro.graphics.commands.TintCommand;

import java.util.ArrayList;

public class CommandsPreset {

	public static final ArrayList<ImageProcessingCommand> Preset = new ArrayList<ImageProcessingCommand>();
	public static final ArrayList<String> Names = new ArrayList<String>();

	static {
		Preset.add(new EmptyCommand());
		Names.add("No Filter");
		Preset.add(new GrayscaleCommand());
		Names.add("Grayscale");
		Preset.add(new TintCommand(30));
		Names.add("Tint 1");
		Preset.add(new TintCommand(70));
		Names.add("Tint 2");
		Preset.add(new BlackFrameCommand());
		Names.add("Black Frame");
		Preset.add(new ColorBoostCommand(Color.RED, 20));
		Names.add("Red Boost");
		Preset.add(new ColorBoostCommand(Color.GREEN, 20));
		Names.add("Green Boost");
		Preset.add(new ColorBoostCommand(Color.BLUE, 20));
		Names.add("Blue Boost");
		Preset.add(new ColorFilterCommand(1.1, 0.7, 0.7));
		Names.add("Color Filter 1");
		Preset.add(new ColorFilterCommand(0.7, 1.1, 0.7));
		Names.add("Color Filter 2");
		Preset.add(new ColorFilterCommand(0.7, 0.7, 1.1));
		Names.add("Color Filter 3");
		Preset.add(new ColorFilterCommand(1.3, 1.1, 0.8));
		Names.add("Color Filter 4");
		Preset.add(new DecreaseColorDepthCommand(128));
		Names.add("Decrease Color Depth");
		Preset.add(new GammaCorrectionCommand(0.6, 0.5, 0.7));
		Names.add("Gamma Correction");
		Preset.add(new InvertColorCommand());
		Names.add("Invert Color");
		Preset.add(new MirrorCommand());
		Names.add("Mirror");
		Preset.add(new SepiaCommand(2, 1, 0, 20));
		Names.add("Sepia");
		Preset.add(new SepiaCommand(2, 2, 0, 20));
		Names.add("Sepia 2");
		Preset.add(new SepiaCommand(1.62, 0.78, 1.21, 20));
		Names.add("Sepia 3");
		Preset.add(new SepiaCommand(1.62, 1.28, 1.01, 45));
		Names.add("Sepia 4");
	}

	public static final Integer[] ImageIds = new Integer[]
			{
			R.mipmap.sample_00, R.mipmap.sample_02,
			R.mipmap.sample_03, R.mipmap.sample_04, R.mipmap.sample_05,
			R.mipmap.sample_06, R.mipmap.sample_07, R.mipmap.sample_08,
			R.mipmap.sample_09, R.mipmap.sample_10, R.mipmap.sample_11,
			R.mipmap.sample_12, R.mipmap.sample_13, R.mipmap.sample_14,
			R.mipmap.sample_15, R.mipmap.sample_16, R.mipmap.sample_17,
			R.mipmap.sample_18, R.mipmap.sample_19, R.mipmap.sample_20,
			};
}
