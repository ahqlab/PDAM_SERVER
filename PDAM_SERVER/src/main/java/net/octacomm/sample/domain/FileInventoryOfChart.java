package net.octacomm.sample.domain;

import lombok.Data;

@Data
public class FileInventoryOfChart extends FileInventory implements Domain {
	
	private int accum;
	
	private int total;
	
	private int total1;
	
	private int total2;
	
	private int total3;
	
	private int total4;

	@Override
	public String toString() {
		return "FileInventoryOfChart [accum=" + accum + ", total=" + total + ", total1=" + total1 + ", total2=" + total2
				+ ", total3=" + total3 + ", total4=" + total4 + ", getFiIdx()=" + getFiIdx() + ", getRegistDate()="
				+ getRegistDate() + ", getPileType()=" + getPileType() + ", getPileStandard()=" + getPileStandard()
				+ ", getConstructionIdx()=" + getConstructionIdx() + ", getFileWeight()=" + getFileWeight()
				+ ", getMeterof51()=" + getMeterof51() + ", getMeterof52()=" + getMeterof52() + ", getMeterof53()="
				+ getMeterof53() + ", getMeterof54()=" + getMeterof54() + ", getMeterof61()=" + getMeterof61()
				+ ", getMeterof62()=" + getMeterof62() + ", getMeterof63()=" + getMeterof63() + ", getMeterof64()="
				+ getMeterof64() + ", getMeterof71()=" + getMeterof71() + ", getMeterof72()=" + getMeterof72()
				+ ", getMeterof73()=" + getMeterof73() + ", getMeterof74()=" + getMeterof74() + ", getMeterof81()="
				+ getMeterof81() + ", getMeterof82()=" + getMeterof82() + ", getMeterof83()=" + getMeterof83()
				+ ", getMeterof84()=" + getMeterof84() + ", getMeterof91()=" + getMeterof91() + ", getMeterof92()="
				+ getMeterof92() + ", getMeterof93()=" + getMeterof93() + ", getMeterof94()=" + getMeterof94()
				+ ", getMeterof101()=" + getMeterof101() + ", getMeterof102()=" + getMeterof102() + ", getMeterof103()="
				+ getMeterof103() + ", getMeterof104()=" + getMeterof104() + ", getMeterof111()=" + getMeterof111()
				+ ", getMeterof112()=" + getMeterof112() + ", getMeterof113()=" + getMeterof113() + ", getMeterof114()="
				+ getMeterof114() + ", getMeterof121()=" + getMeterof121() + ", getMeterof122()=" + getMeterof122()
				+ ", getMeterof123()=" + getMeterof123() + ", getMeterof124()=" + getMeterof124() + ", getMeterof131()="
				+ getMeterof131() + ", getMeterof132()=" + getMeterof132() + ", getMeterof133()=" + getMeterof133()
				+ ", getMeterof134()=" + getMeterof134() + ", getMeterof141()=" + getMeterof141() + ", getMeterof142()="
				+ getMeterof142() + ", getMeterof143()=" + getMeterof143() + ", getMeterof144()=" + getMeterof144()
				+ ", getMeterof151()=" + getMeterof151() + ", getMeterof152()=" + getMeterof152() + ", getMeterof153()="
				+ getMeterof153() + ", getMeterof154()=" + getMeterof154() + ", getMeterof161()=" + getMeterof161()
				+ ", getMeterof162()=" + getMeterof162() + ", getMeterof163()=" + getMeterof163() + ", getMeterof164()="
				+ getMeterof164() + ", getMeterof171()=" + getMeterof171() + ", getMeterof172()=" + getMeterof172()
				+ ", getMeterof173()=" + getMeterof173() + ", getMeterof174()=" + getMeterof174() + ", getMeterof181()="
				+ getMeterof181() + ", getMeterof182()=" + getMeterof182() + ", getMeterof183()=" + getMeterof183()
				+ ", getMeterof184()=" + getMeterof184() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + "]";
	}
	
	
	
}
