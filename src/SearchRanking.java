class SearchRanking {

    static class Applicant {
        String language, tech, year, food;
        int score;
        public Applicant(String info) {
            String[] infos = info.split(" ");
            language = infos[0];
            tech = infos[1];
            year = infos[2];
            food = infos[3];
            score = Integer.parseInt(infos[4]);
        }
        int isPass(Applicant query) {
            if(!query.language.equals("-") && !query.language.equals(this.language)) return 0;
            if(!query.tech.equals("-") && !query.tech.equals(this.tech)) return 0;
            if(!query.year.equals("-") && !query.year.equals(this.year)) return 0;
            if(!query.food.equals("-") && !query.food.equals(this.food)) return 0;
            if(this.score < query.score) return 0;
            return 1;
        }
    }

    public static int[] solution(String[] info, String[] query) {
        int[] answer = {};
        answer = new int[query.length];
        Applicant[] applicants = new Applicant[info.length];
        for(int i = 0; i < info.length; i++) applicants[i] = new Applicant(info[i]);
        for(int i = 0; i < query.length; i++) {
            Applicant condition = new Applicant(query[i].replaceAll("and ", ""));
            int pass = 0;
            for(Applicant a : applicants) pass += a.isPass(condition);
            answer[i] = pass;
        }
        return answer;
    }

    public static void main(String[] args) {

    }
}