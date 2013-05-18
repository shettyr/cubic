package com.rk.cubic.level;

import android.util.Log;

public enum Level {

	BEGINNER(0,10),
	INTERMEDIATE(1,20),
	ADVANCED(2,30);
	
	
	private final String[] beginnerList = {"heart","mournful", "horticulture","duple","disease","impetuous","insurmountable","forestall","congeal","guitar","fortification","marinate","referendum","percolate","animosity","invertebrate","atrium","posterity","incorruptible","centralization","percussion","abdomen","summability","palette","innate","sternum","oxygen","erosion","futurity","dribble","consecrate","stallion","companion","extinction","abdicate","renounce","perishable","starvation",
		"ammunition","bible","fortification","dominion","dozen","sternum","electrochemical","bicarmel","paralegal","geranium","goal","thermal","piecemeal","toploftical","shoal","spinal","jovial","dismal","immortal","stalemate"};

	private final String[] intermediateList = {"auxiliary","innocuous","progenitor","fortitude","proclivity","embarrassed","panacea","anarchy","opprobrium","vindictive","ecstasy","exuberance","conscientious","condescend", "acquaintance","amateur","hygiene","abnegate","Sordid","cacophony","affluent","sleuth","maneuver","cinematography","abalone","ablution","aboriginally","absolution","babka","badminton","balanoid","ballistic","caballero","cabriolet","cabrito","cachet","concoction","dachshund","dactyl","debacle","debris","eatage","ecclesiastic","eddyroot","edifices","epitome","facetious","fallow","fandango","fathom","gadroon","gallows","galvanic","gantlet","haggis","haiku","halide","hamartia","idolatrous","ignoramus","illegible","illimitable","javelin","jnana","jocular","juggernaut","Kallikak","karma","kathakali","keelhaul","laity","lama","lampoon","lamprey","macadamia","Mach","madrepore","maestro","nabob","nacelle","narcissistic","narcissus","obliged","oblique","obliterate","occidental","obsequious","padre","palberry","pallid","palooka","quadrilingual","quadruped","quietus","quinine","ramulose","ranunculus","readjourn","realschule","sable","sabotage","salivate","salmonella","tactically","tahini","taiga","tandoori","ubiquitous","ultimo","umlaut","unadulterated","venerable","venue","verbena","verboten","wamble","warren","whodunit","willowware","xenial","xylophone","Zendo","zenith",
		"opportunity","eligible","suspicious","undoubtedly","psychology","efficient","minuscule","condemn","jewelry","exaggerate","liaison","resemblance"};
		
	private final String[] advancedList = {"lackadaisical","irascible","lachrymose","Scrupulous","Peremptory","quibble","Supercilious","Conspicuous","bellicose","aficionado","scrumptious","inundate","pugnacious","Abattoir" };
	
	private String[] wordList = {};
	private int points = 0;
	private int level = 0;
	
	Level(int level, int points){
		this.level = level;
		this.points = points;
		switch(level){
			case(2):
				this.wordList = advancedList;
				break;
			case(1):
				this.wordList = intermediateList;
				break;
			default:
				this.wordList = beginnerList;
		}

	}
	
	public String[] getWordList() {
		return wordList;
	}
	
	
	public int getLevel(){
		return level;
	}
	
	public int getPoints(){
		return points;
	}
	
	public static Level getLevel(int level){
		Log.i("Level","getLevel received " + level);
		switch(level){
			case(2):
				return ADVANCED;
			case(1):
				return INTERMEDIATE;
			default:
				return BEGINNER;
		}		
	}
}
