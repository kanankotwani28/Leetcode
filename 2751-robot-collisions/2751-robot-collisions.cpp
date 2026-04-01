class Solution {
public:
    vector<int> survivedRobotsHealths(vector<int>& positions, vector<int>& healths, string directions) {
        int n = positions.size();
        
        vector<tuple<int,int,char,int>> robots;
        for(int i = 0; i < n; i++) {
            robots.push_back({positions[i], healths[i], directions[i], i});
        }

        // Sort by position
        sort(robots.begin(), robots.end());

        stack<int> st; // indices in robots vector

        for(int i = 0; i < n; i++) {
            auto &[pos, health, dir, idx] = robots[i];

            if(dir == 'R') {
                st.push(i);
            } 
            else {
                // dir == 'L'
                while(!st.empty() && robots[st.top()].get<1>() < health) {
                    // R dies
                    health--;
                    robots[st.top()].get<1>() = 0;
                    st.pop();
                }

                if(!st.empty()) {
                    if(robots[st.top()].get<1>() == health) {
                        // both die
                        robots[st.top()].get<1>() = 0;
                        health = 0;
                        st.pop();
                    } else {
                        // R survives
                        robots[st.top()].get<1>()--;
                        health = 0;
                    }
                }
            }
        }

        // Collect survivors
        vector<pair<int,int>> survivors;
        for(auto &[pos, health, dir, idx] : robots) {
            if(health > 0) {
                survivors.push_back({idx, health});
            }
        }

        // Sort by original index
        sort(survivors.begin(), survivors.end());

        vector<int> result;
        for(auto &p : survivors) {
            result.push_back(p.second);
        }

        return result;
    }
};