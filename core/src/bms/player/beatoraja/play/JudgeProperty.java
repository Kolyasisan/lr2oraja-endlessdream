package bms.player.beatoraja.play;

/**
 * 判定の設定値
 *
 * @author exch
 */
public enum JudgeProperty {

    FIVEKEYS(new long[][]{ {-20000, 20000}, {-50000, 50000}, {-100000, 100000}, {-150000, 150000}, {-150000, 500000} },
            new long[][]{ {-30000, 30000}, {-60000, 60000}, {-110000, 110000}, {-160000, 160000}, {-160000, 500000}},
            new long[][]{ {-120000, 120000}, {-150000, 150000}, {-200000, 200000}, {-250000, 250000}},
            0,
            new long[][]{ {-130000, 130000}, {-160000, 160000}, {-110000, 110000}, {-260000, 260000}},
            0,
            new boolean[]{true, true, true, false, false, false },
            MissCondition.ALWAYS,
            new boolean[]{true, true, true, true, true, false },
            JudgeWindowRule.NORMAL
            ),
    SEVENKEYS(new long[][]{ {-20000, 20000}, {-60000, 60000}, {-150000, 150000}, {-280000, 220000}, {-150000, 500000} },
            new long[][]{ {-30000, 30000}, {-70000, 70000}, {-160000, 160000}, {-290000, 230000}, {-160000, 500000}},
            new long[][]{ {-120000, 120000}, {-160000, 160000}, {-200000, 200000}, {-280000, 220000}},
            0,
            new long[][]{ {-130000, 130000}, {-170000, 170000}, {-210000, 210000}, {-290000, 230000}},
            0,
            new boolean[]{true, true, true, false, false, true },
            MissCondition.ALWAYS,
            new boolean[]{true, true, true, true, true, false },
            JudgeWindowRule.NORMAL
            ),
    PMS(new long[][]{ {-20000, 20000}, {-50000, 50000}, {-117000, 117000}, {-183000, 183000}, {-175000, 500000} },
            new long[][]{},
            new long[][]{ {-120000, 120000}, {-150000, 150000}, {-217000, 217000}, {-283000, 283000}},
            200000,
            new long[][]{},
            0,
            new boolean[]{true, true, true, false, false, false },
            MissCondition.ONE,
            new boolean[]{true, true, true, false, true, false },
            JudgeWindowRule.PMS
            ),
    KEYBOARD(new long[][]{ {-30000, 30000}, {-90000, 90000}, {-200000, 200000}, {-320000, 240000}, {-200000, 650000} },
            new long[][]{},
            new long[][]{ {-160000, 25000}, {-200000, 75000}, {-260000, 140000}, {-320000, 240000}},
            0,
            new long[][]{},
            0,
            new boolean[]{true, true, true, false, false, true },
            MissCondition.ALWAYS,
            new boolean[]{true, true, true, true, true, false },
            JudgeWindowRule.NORMAL
            ),
    LR2(new long[][]{{-21000, 21000}, {-60000, 60000}, {-120000, 120000}, {-200000, 200000}, {0, 1000000}}, 
            new long[][]{{-21000, 21000}, {-60000, 60000}, {-120000, 120000}, {-200000, 200000}, {0, 1000000}}, 
            new long[][]{{-120000, 120000}, {-120000, 120000}, {-120000, 120000}, {-200000, 200000}},
            0,
            new long[][]{{-120000, 120000}, {-120000, 120000}, {-120000, 120000}, {-200000, 200000}}, 
            0,
            new boolean[]{true, true, true, false, false, true },
            MissCondition.ALWAYS,
            new boolean[]{true, true, true, true, true, false },
            JudgeWindowRule.LR2
            ),

            // Kolyasisan: IIDX-accurate timing (somewhat? need to double-check LNs and scratches), ceiled to nearest millisecond.
            // useful for using raja for training with more accurate timing.
    IIDX(new long[][]{ {-17000, 17000}, {-34000, 34000}, {-117000, 117000}, {-250000, 250000}, {-500000, 500000} },
            new long[][]{ {-17000, 17000}, {-34000, 34000}, {-117000, 117000}, {-250000, 250000}, {-500000, 500000}},
            new long[][]{ {-17000, 17000}, {-34000, 34000}, {-117000, 117000}, {-250000, 250000}},
            0,
            new long[][]{ {-17000, 17000}, {-34000, 34000}, {-117000, 117000}, {-250000, 250000}},
            0,
            new boolean[]{true, true, true, false, false, true },
            MissCondition.ALWAYS,
            new boolean[]{true, true, true, true, true, false },
            JudgeWindowRule.IIDX
            ),
    ;

    /**
     * 通常ノートの格判定幅。PG, GR, GD, BD, MSの順で{LATE下限, EARLY上限}のセットで表現する。
     */
    private final long[][] note;
    /**
     * スクラッチノートの格判定幅。PG, GR, GD, BD, MSの順で{LATE下限, EARLY上限}のセットで表現する。
     */
    private final long[][] scratch;
    /**
     * 通常ロングノート終端の格判定幅。PG, GR, GD, BD, MSの順で{LATE下限, EARLY上限}のセットで表現する。
     */
    private final long[][] longnote;
    
    public final long longnoteMargin;
    /**
     * スクラッチロングノート終端の格判定幅。PG, GR, GD, BD, MSの順で{LATE下限, EARLY上限}のセットで表現する。
     */
    private final long[][] longscratch;
    
    public final long longscratchMargin;

    /**
     * 各判定毎のコンボ継続
     */
    public final boolean[] combo;
    /**
     * MISSの発生回数
     */
    public final MissCondition miss;
    /**
     * 各判定毎のノートの判定を消失するかどうか。PG, GR, GD, BD, PR, MSの順
     */
    public final boolean[] judgeVanish;
    
    public final JudgeWindowRule windowrule;

    private JudgeProperty(long[][] note, long[][] scratch, long[][] longnote, long longnoteMargin, long[][] longscratch, long longscratchMargin, boolean[] combo, MissCondition miss, boolean[] judgeVanish, JudgeWindowRule windowrule) {
        this.note = note;
        this.scratch = scratch;
        this.longnote = longnote;
        this.longnoteMargin = longnoteMargin;
        this.longscratch = longscratch;
        this.longscratchMargin = longscratchMargin;
        this.combo = combo;
        this.miss = miss;
        this.judgeVanish = judgeVanish;
        this.windowrule = windowrule;
    }

    public int[][] getNoteJudge(int judgerank, int[] judgeWindowRate) {
        return convertMilli(windowrule.create(note, judgerank, judgeWindowRate));
    }

    public int[][] getLongNoteEndJudge(int judgerank, int[] judgeWindowRate) {
        return convertMilli(windowrule.create(longnote, judgerank, judgeWindowRate));
    }

    public int[][] getScratchJudge(int judgerank, int[] judgeWindowRate) {
        return convertMilli(windowrule.create(scratch, judgerank, judgeWindowRate));
    }

    public int[][] getLongScratchEndJudge(int judgerank, int[] judgeWindowRate) {
        return convertMilli(windowrule.create(longscratch, judgerank, judgeWindowRate));
    }
    
    private int[][] convertMilli(long[][] judge) {
        int[][] mjudge = new int[judge.length][];
        for(int i = 0;i < mjudge.length;i++) {
            mjudge[i] = new int[judge[i].length];
            for(int j = 0;j < mjudge[i].length;j++) {
                mjudge[i][j] = (int) (judge[i][j] / 1000);              
            }
        }
        return mjudge;
    }
    
    public long[][] getJudge(NoteType notetype, int judgerank, int[] judgeWindowRate) {
        switch(notetype) {
        case NOTE:
            return windowrule.create(note, judgerank, judgeWindowRate);
        case LONGNOTE_END:
            return windowrule.create(longnote, judgerank, judgeWindowRate);
        case SCRATCH:
            return windowrule.create(scratch, judgerank, judgeWindowRate);
        case LONGSCRATCH_END:
            return windowrule.create(longscratch, judgerank, judgeWindowRate);
        default:
            return windowrule.create(note, judgerank, judgeWindowRate);
        }
    }
    
    public enum MissCondition {
        ONE, ALWAYS
    }
    
    public enum NoteType {
        NOTE, LONGNOTE_END, SCRATCH, LONGSCRATCH_END
    }
    
    public enum JudgeWindowRule {
        NORMAL (new int[]{25, 50, 75, 100, 125}, new boolean[]{false, false, false, false, true}),
        PMS (new int[]{33, 50, 70, 100, 133}, new boolean[]{true, false, false, true, true}),
        LR2 (new int[]{25, 50, 75, 100, 75}, new boolean[]{false, false, false, true, true}) {
            @Override
            public long[][] create(long[][] org, int judgerank, int[] judgeWindowRate) {
                return JudgeWindowRule.createLR2(org, judgerank, judgeWindowRate);
            }
        },

        // Kolyasisan: I do not want this! All timings should be scaling to 100.
        // Judge gimmick bullshit like Gambol SPA seriously gets on my nerves, that's just not fun.
        // Easy is the default rank for most BMS either way (index 3), but beatoraja selects normal if the rank property is out of bounds??? (index 2).
        // Lock down from ever being modified.
        IIDX (new int[]{100, 100, 100, 100, 100}, new boolean[]{true, true, true, true, true}),;
        
        /**
         * JUDGERANKの倍率(VERYHARD, HARD, NORMAL, EASY, VERYEASY)
         */
        public final int[] judgerank;
        /**
         * 各判定幅をjudgerankによらず固定にするかどうか(PG, GR, GD, BD, MSの順)
         */
        public final boolean[] fixjudge;

        private static final long[][] LR2_SCALING = {
            {0,0,0,0,0},
            {0,8000,15000,18000,21000}, // PGREAT
            {0,24000,30000,40000,60000}, // GREAT
            {0,40000,60000,100000,120000}, // GOOD
        };

        private static long lr2JudgeScaling(long base, int judgerank) {
            long sign = 1;
            if (base < 0) {
                base = -base;
                sign = -1;
            }
            if (judgerank >= 100) {
                return sign*base*judgerank/100;
            } else {
                final int last = LR2_SCALING[0].length-1;
                // judgerank < 100
                final int judgeindex = judgerank/25;
                int s = 0;
                while (s < LR2_SCALING.length && base >= LR2_SCALING[s][last]) {
                    ++s;
                }
                long n, d, x1, x2;
                if (s < LR2_SCALING.length) {
                    n = base - LR2_SCALING[s-1][last];
                    d = LR2_SCALING[s][last] - LR2_SCALING[s-1][last];
                    x1 = d*LR2_SCALING[s-1][judgeindex] + n*(LR2_SCALING[s][judgeindex]-LR2_SCALING[s-1][judgeindex]);
                    x2 = d*LR2_SCALING[s-1][judgeindex+1] + n*(LR2_SCALING[s][judgeindex+1]-LR2_SCALING[s-1][judgeindex+1]);
                } else {
                    n = base;
                    d = LR2_SCALING[s-1][last];
                    x1 = n*LR2_SCALING[s-1][judgeindex];
                    x2 = n*LR2_SCALING[s-1][judgeindex+1];
                }
                return sign*(x1 + (judgerank-judgeindex*25)*(x2-x1)/25)/d;
            }
        }

        private static long[][] createLR2(long[][] org, int judgerank, int[] judgeWindowRate) {
            final long[][] judge = new long[org.length][2];
            for (int i = 0; i < judge.length; i++) {
                for(int j = 0; j < judge[i].length; j++) {
                    judge[i][j] = org[i][j];
                }
            }
            // Interpolate LR2 Judge windows
            {
                // only change pgreat, great, good
                final int fixmax = 3;
                for (int i = 0; i < fixmax; i++) {
                    for(int j = 0; j < 2; j++) {
                        judge[i][j] = lr2JudgeScaling(org[i][j], judgerank);
                    }
                }

                // correction if we exceed the bad windows
                for (int i = fixmax-1; i >= 0; i--) {
                    for(int j = 0; j < 2; j++) {
                        if(Math.abs(judge[i][j]) > Math.abs(judge[i+1][j])) {
                            judge[i][j] = judge[i+1][j];
                        }
                    }
                }
            }

            // judgeWindowRateによる補正
            for (int i = 0; i < Math.min(org.length, 3); i++) {
                for(int j = 0;j < 2;j++) {
                    judge[i][j] = judge[i][j]*judgeWindowRate[i] / 100;
                    if(Math.abs(judge[i][j]) > Math.abs(judge[3][j])) {
                        judge[i][j] = judge[3][j];
                    }
                    if(i > 0 && Math.abs(judge[i][j]) < Math.abs(judge[i - 1][j])) {
                        judge[i][j] = judge[i - 1][j];
                    }
                }
            }
            
            return judge;
        }

        public long[][] create(long[][] org, int judgerank, int[] judgeWindowRate) {
            final long[][] judge = new long[org.length][2];
            for (int i = 0; i < judge.length; i++) {
                for(int j = 0;j < 2;j++) {
                    judge[i][j] = fixjudge[i] ? org[i][j] : org[i][j] * judgerank / 100;
                }
            }

            int fixmin = -1;
            for (int i = 0; i < Math.min(org.length, 4); i++) {
                if(fixjudge[i]) {
                    fixmin = i;
                    continue;
                }
                int fixmax = -1;
                for(int j = i + 1;j < 4;j++) {
                    if(fixjudge[j]) {
                        fixmax = j;
                        break;
                    }
                }
                
                for(int j = 0;j < 2;j++) {
                    if(fixmin != -1 && Math.abs(judge[i][j]) < Math.abs(judge[fixmin][j])) {
                        judge[i][j] = judge[fixmin][j];
                    }
                    if(fixmax != -1 && Math.abs(judge[i][j]) > Math.abs(judge[fixmax][j])) {
                        judge[i][j] = judge[fixmax][j];
                    }
                }
            }

            // judgeWindowRateによる補正
            for (int i = 0; i < Math.min(org.length, 3); i++) {
                for(int j = 0;j < 2;j++) {
                    judge[i][j] = judge[i][j]*judgeWindowRate[i] / 100;
                    if(Math.abs(judge[i][j]) > Math.abs(judge[3][j])) {
                        judge[i][j] = judge[3][j];
                    }
                    if(i > 0 && Math.abs(judge[i][j]) < Math.abs(judge[i - 1][j])) {
                        judge[i][j] = judge[i - 1][j];
                    }
                }
            }
            
            return judge;
        }
        
        private JudgeWindowRule(int[] judgerank, boolean[] fixjudge) {
            this.judgerank = judgerank;
            this.fixjudge = fixjudge;
        }
    }
}
