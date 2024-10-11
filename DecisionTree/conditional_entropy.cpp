#include <iostream>
#include <vector>
#include <string>
#include <unordered_map>
#include <cmath>
#include <memory>

struct TreeNode;
struct EdgeNode;

typedef std::string tree_t;
TreeNode* t;
std::vector<std::string> attributes;

struct EdgeNode {
    tree_t val;
    std::shared_ptr<TreeNode> subtree;
    std::shared_ptr<EdgeNode> next;
};

struct TreeNode {
    tree_t val;
    std::shared_ptr<EdgeNode> subtree_l;
};

double calculateEntropy(const std::vector<std::vector<std::string>>& data, int targetAttrIndex) {
    std::unordered_map<std::string, int> freq;
    for (const auto& row : data) {
        freq[row[targetAttrIndex]]++;
    }

    double entropy = 0.0;
    int dataSize = data.size();
    for (const auto& pair : freq) {
        double prob = static_cast<double>(pair.second) / dataSize;
        entropy -= prob * std::log2(prob);
    }

    return entropy;
}

double calculateInformationGain(const std::vector<std::vector<std::string>>& data, int attrIndex, int targetAttrIndex) {
    double totalEntropy = calculateEntropy(data, targetAttrIndex);
    std::unordered_map<std::string, std::vector<std::vector<std::string>>> subsets;

    for (const auto& row : data) {
        subsets[row[attrIndex]].push_back(row);
    }

    double subsetEntropy = 0.0;
    int dataSize = data.size();
    for (const auto& pair : subsets) {
        double subsetProb = static_cast<double>(pair.second.size()) / dataSize;
        subsetEntropy += subsetProb * calculateEntropy(pair.second, targetAttrIndex);
    }

    return totalEntropy - subsetEntropy;
}

std::shared_ptr<TreeNode> buildDecisionTree(const std::vector<std::vector<std::string>>& data, const std::vector<std::string>& attributes, int targetAttrIndex) {
    if (data.empty()) {
        return nullptr;
    }

    // Check if all the target values are the same
    bool allSame = true;
    std::string firstVal = data[0][targetAttrIndex];
    for (const auto& row : data) {
        if (row[targetAttrIndex] != firstVal) {
            allSame = false;
            break;
        }
    }

    if (allSame) {
        auto leafNode = std::make_shared<TreeNode>();
        leafNode->val = firstVal;
        return leafNode;
    }

    // Find the attribute with the highest information gain
    int bestAttrIndex = -1;
    double maxGain = -1.0;
    for (int i = 0; i < attributes.size(); ++i) {
        if (i == targetAttrIndex) continue;
        double gain = calculateInformationGain(data, i, targetAttrIndex);
        if (gain > maxGain) {
            maxGain = gain;
            bestAttrIndex = i;
        }
    }

    if (bestAttrIndex == -1) {
        return nullptr;
    }

    auto root = std::make_shared<TreeNode>();
    root->val = attributes[bestAttrIndex];

    std::unordered_map<std::string, std::vector<std::vector<std::string>>> subsets;
    for (const auto& row : data) {
        subsets[row[bestAttrIndex]].push_back(row);
    }

    std::shared_ptr<EdgeNode> previousEdge = nullptr;
    for (const auto& pair : subsets) {
        auto edge = std::make_shared<EdgeNode>();
        edge->val = pair.first;
        edge->subtree = buildDecisionTree(pair.second, attributes, targetAttrIndex);
        edge->next = previousEdge;
        previousEdge = edge;
    }

    root->subtree_l = previousEdge;
    return root;
}

void printTree(const std::shared_ptr<TreeNode>& node, const std::string& indent = "") {
    if (!node) {
        return;
    }
    
    std::cout << indent << node->val << std::endl;
    
    auto edge = node->subtree_l;
    while (edge) {
        std::cout << indent << "  -- " << edge->val << " -->" << std::endl;
        printTree(edge->subtree, indent + "    ");
        edge = edge->next;
    }
}



int main() {
    
    std::vector<std::vector<std::string>> input1 {
        {"temperature", "rain", "wind", "quality"},
        {"high", "yes", "light", "acceptable"},
        {"low", "yes", "light", "acceptable"},
        {"low", "no", "moderate", "good"},
        {"high", "yes", "strong", "poor"},
        {"high", "yes", "moderate", "acceptable"},
        {"high", "no", "moderate", "good"},
        {"low", "yes", "strong", "poor"},
        {"high", "no", "light", "good"},
        {"low", "yes", "moderate", "poor"},
        {"high", "no", "strong", "poor"}
    };

    std::vector<std::string> attributes = input1[0];
    input1.erase(input1.begin());

    int targetAttrIndex = attributes.size() - 1; // Assuming the last attribute is the target

    auto decisionTree = buildDecisionTree(input1, attributes, targetAttrIndex);

    // You can add a function to print the tree for visualization
    printTree(decisionTree);

    return 0;
}
