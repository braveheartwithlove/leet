721. Accounts Merge
Given a list accounts, each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some email that is common to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.

Example 1:
Input: 
accounts = [["John", "johnsmith@mail.com", "john00@mail.com"], ["John", "johnnybravo@mail.com"], ["John", "johnsmith@mail.com", "john_newyork@mail.com"], ["Mary", "mary@mail.com"]]
Output: [["John", 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com'],  ["John", "johnnybravo@mail.com"], ["Mary", "mary@mail.com"]]
Approach #1: Depth-First Search [Accepted]
Intuition

Draw an edge between two emails if they occur in the same account. The problem 
comes down to finding connected components of this graph.

Algorithm

For each account, draw the edge from the first email to all other emails.
 Additionally, we'll remember a map from emails to names on the side. After 
 finding each connected component using a depth-first search, we'll add that to our answer.
 
 Approach #1: Depth-First Search [Accepted]
O(Sigma (AilogAi) O(Ai)
 class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToName = new HashMap();
        Map<String, ArrayList<String>> graph = new HashMap();
        for (List<String> account: accounts) {
            String name = "";
            for (String email: account) {
                if (name == "") {
                    name = email;
                    continue;
                }
                graph.computeIfAbsent(email, x-> new ArrayList<String>()).add(account.get(1));
                graph.computeIfAbsent(account.get(1), x-> new ArrayList<String>()).add(email);
                emailToName.put(email, name);
            }
        }

        Set<String> seen = new HashSet();
        List<List<String>> ans = new ArrayList();
        for (String email: graph.keySet()) {
            if (!seen.contains(email)) {
                seen.add(email);
                Stack<String> stack = new Stack();
                stack.push(email);
                List<String> component = new ArrayList();
                while (!stack.empty()) {
                    String node = stack.pop();
                    component.add(node);
                    for (String nei: graph.get(node)) {
                        if (!seen.contains(nei)) {
                            seen.add(nei);
                            stack.push(nei);
                        }
                    }
                }
                Collections.sort(component);
                component.add(0, emailToName.get(email));
                ans.add(component);
            }
        }
        return ans;
    }
}

#happygirl
class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        List<List<String>> res = new ArrayList<>();
        if (accounts.size() == 0) return res;
        Map<String, Set<String>> g = new HashMap<>();
        Map<String, String> EmailToName = new HashMap<>();
        buildGraph(g, accounts, EmailToName);
        
        Set<String> visited = new HashSet<>();
        for (String mail: EmailToName.keySet()) {
            String name = EmailToName.get(mail);
            List<String> mails = new ArrayList<>();
            if (visited.add(mail)) {
                dfs(mails, mail, g, visited);
                Collections.sort(mails);
                mails.add(0, name);
                res.add(mails);
            }
        }
        
        return res;
    }
    
    private void dfs(List<String> mails, String mail, Map<String, Set<String>> g, Set<String> visited) {
        mails.add(mail);
        if (g.get(mail).size() == 0) return;
        for (String neigh: g.get(mail)) {
            if (visited.add(neigh)) {
                dfs(mails, neigh, g, visited);
            }
        }
    }
    
    private void buildGraph(Map<String, Set<String>> g, List<List> accounts, Map<String, String> EmailToName) {
        for (List<String> account: accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String mail = account.get(i);
                EmailToName.put(mail, name);
                g.putIfAbsent(mail, new HashSet<>());
                if (i == 1) continue;
                String prev = account.get(i - 1);
                g.get(prev).add(mail);
                g.get(mail).add(prev);
            }
        }
    }
}
         