pragma solidity >=0.7.0 <0.9.0;
//SPDX-License-Identifier: UNLICENSE

contract Voting {
    
    struct Voter {
        string name;    // voter's name
        uint sharesNum;  // how many voter hold
        uint[] votes;   // the number of votes voter have
        bool[] isVoted; 
    }
    
    struct Proposal {
        string name;
        uint voteCount;
        uint agree;
        uint disagree;
    }
    
    address public shareholders;
    
    mapping(address => Voter) public voters;
    
    Proposal[] public proposals;
    
    constructor(string memory _name,uint _sharesNum, string[] memory _proposalsNames) {
        shareholders = msg.sender;
        voters[shareholders].name = _name;
        voters[shareholders].sharesNum = _sharesNum;
        
        for (uint i=0; i< _proposalsNames.length;i++) {
            proposals.push(Proposal({
                name: _proposalsNames[i],
                voteCount:0,
                agree:0,
                disagree:0
            }));
            voters[shareholders].isVoted.push(false);
            voters[shareholders].votes.push(0);
        }
    }
    
    function giveRightToVote(address voter, uint proposal) public {
        require(
            !voters[voter].isVoted[proposal],
            "The voter already voted"
        );
        require(
            voters[voter].votes[proposal] == 0
        );
        voters[voter].votes[proposal] = voters[voter].sharesNum;
    }

    function checkRightToVote(address voter, uint proposal) public view
        returns (uint voteCount_)
    {
        voteCount_ = voters[voter].votes[proposal];
    }
    
    function vote(uint proposal, bool choice, uint votes) public {
        Voter storage sender = voters[msg.sender];
        require(sender.sharesNum != 0, "Has no right to vote");
        require((!sender.isVoted[proposal]||voters[shareholders].votes[proposal]!=0), "Already voted");
        require(voters[shareholders].votes[proposal]<votes, "Not enough votes");
        sender.isVoted[proposal] = true;
        if(choice){
            proposals[proposal].agree += votes;
        }
        else{
            proposals[proposal].disagree += votes;
        }
        voters[shareholders].votes[proposal] -= votes;
        if(voters[shareholders].votes[proposal] == voters[shareholders].sharesNum){
            proposals[proposal].voteCount += 1;
        }
    }
    
    function getPassedProposalResult() public view
        returns (string memory winningProposal_, uint voteCount_, uint agree_, uint disagree_)
    {
           for (uint proposal = 0; proposal < proposals.length; proposal++) {
               if (proposals[proposal].agree > proposals[proposal].disagree) {
                   winningProposal_ = proposals[proposal].name;
                   voteCount_ = proposals[proposal].voteCount;
                   agree_ =  proposals[proposal].agree;
                   disagree_ = proposals[proposal].disagree;
               }
           }
    }
    
}