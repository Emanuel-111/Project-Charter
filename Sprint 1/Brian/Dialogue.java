package com.storygame.game;

import java.util.ArrayList;

public class Dialogue {

    int currentAct = 0;
    ArrayList<String[]> npcText;
    ArrayList<String> mainDialogue;
    String tutorialText;

    public void setCurrentAct(int act) {
        currentAct = act;
    }

    public String getNPCText(int npcId) {
        return npcText.get(currentAct)[npcId];
    }

    public String getMainDialogueText(int talkingPhase) {
        return mainDialogue.get(talkingPhase);
    }

    public String getTutorialText() {
        return tutorialText;
    }

    public Dialogue() {
        npcText = new ArrayList<>();
        mainDialogue = new ArrayList<>();
        this.setNPCText();
        this.setMainDialogue();
        // This is the text used during the tutorial of the game.
        tutorialText = "0Swift: Welcome to picross! This is going to be where we design our inventions." +
        "0Swift: First thing you might notice is the information in the top-left corner.&" +
        "0Swift: You have the options screen which you can click to enter the options menu.&" +
        "0Swift: Next to that is a skip button. You can skip all inventions because I will do them for you.&" +
        "0Swift: You can use the button skip button an unlimited amount of times whenever you feel stuck." +
        "0Swift: The timer helps you keep track of how long it is taking you to complete each puzzle." +
        "0Swift: Alright. Now that is cleared away. I want you to notice all these numbers that are displayed.&" +
        "0Swift: These numbers tell you how to put the puzzle together. Now look at the board itself.&" +
        "0Swift: You can use the Arrow Keys to move the highlighted box around the board." +
        "0Swift: Each square on the board can be filled in by pressing the Z Key." +
        "0Swift: The idea is to fill in the correct squares. This will create an image which is the invention." +
        "0Swift: You use the numbers around the board to determine what squares to fill in.&" +
        "0Swift: You read the numbers from the left to right, or top to bottom." +
        "0Swift: The size of the number tells you how many connecting squares to fill in.&" +
        "0Swift: If it is one then you fill in one square, but what if it is greater than one?" +
        "0Swift: If the number is three you fill in three squares that are NEXT to each other.&" +
        "0Swift: The numbers on the left correspond to filling squares horizontally.&" +
        "0Swift: The numbers on the top correspond to filling squares vertically.&" +
        "0Swift: But what if there are multiple numbers in each column?&" +
        "0Swift: You fill in the first number then leave a space then fill in the second number.&" +
        "0Swift: The space between each number can vary. Sometimes it can be five spaces between each number." +
        "0Swift: But the leftmost/topmost number number will always be before the rightmost/bottommost." +
        "0Swift: Sometimes you can guess where numbers might be placed." +
        "0Swift: Other times you might know for sure a number does not go in a location.&" +
        "0Swift: This could be because there is zero in the column. Meaning nothing to fill in.&" +
        "0Swift: You can cross out the square by pressing the X key." +
        "0Swift: Crossing out squares helps you remember you should not fill the square in." +
        "0Swift: If you try to fill in a square that should not be filled in it will correct it.&" +
        "0Swift: It will autocorrect your mistake. So you can only fill in squares you are meant to." +
        "0Swift: Enough of me talking. If you get stuck remember the skip button." +
        "0&  ";
    }

    // The npc text is structured by act. Each act has its own npc in the act.
    // You need a act and npcId to get their corresponding text.
    // Here we is a table for each symbol used and its corresponding action
    /*
    Symbol | Action
    -------|-------
        @  | A question is coming; The first is the text to the answer to yes and the second @ Text is for a no answer.
        #  | This means a picross is coming and a number follows 0-1-2 to show the picross size.
        ^  | This means that the act is ending and the next one should be updated.
        *  | This is the default text after you have talked to the characters once.
        &  | This is if there is a special event playing i.e. an animation coming. 
           |
    */
    public void setNPCText() {
        // 0 = Swift : 1 = Player : 2 = NPC : 3 = Owl
        // Act one              // Question
        npcText.add(new String[]{"2Hi welcome to our little slice of life, we don't have a lot but we have a local mechanic shop down east.",
            "2Every time I workout I get so thirsty, could you help me make something to fix that?%@2Alright well hopefully I will find something.@2Thank you so much.#0" + 
            "1Finch: this bottle takes water molecules from the air outside and stores it for future use." + 
            "2Thanks a lot!",
            "2My family doesn't have a lot of money to buy me any toys, do you want to help make me one?%@2Maybe I can find a way to make money!@2Yay!#0" + 
            "2Thank you so much, now I won't get so bored anymore!",
            "2I'm so hungry, do you think you can make me something to eat?%@2I’ll try to find someone who can me then.@2You're so kind.#0" + 
            "1Finch: This seed will grow into a special fast growing fruit tree, just keep replanting new ones to have a huge supply of food." + 
            "2Thanks now I can sell the unhealthy fruit to get actual food like Pizza and French Fries.",
            "2Mechanic: How are you guys doing! I'm the local mechanic here. Need some help? " + 
            "0Swift: We need an electric battery. My car broke down." +
            "2Mechanic: That will be about this much... " + 
            "0Swift: WHAT?! We can't afford that!&" + 
            "2Mechanic: Alright. Well it's your lucky day because word spread that you have been helping around a lot. For folks like you I'll give you the battery for nothing. You just need to go back and put in the car." +
            "2If you can't pay with money then try helping the people around town doing some work and then come back to me when you're done."
        });
        // Act two
        npcText.add(new String[]{"2Hello welcome to our home we are the spiritual builders, if you seek a guide please visit Master Crane at the top of the mountain. ",
            "2Recently my pillow has been aching my back when I do my meditation, can you help me fix it?%@2I will try to forget the pain.@2Thank you.#1 " + 
            "1Finch: Use this to call a chiropractor and get some help. " + 
            "2But we have no reception up on this mountain. " + 
            "1Finch: This isn't any normal phone, it's enhanced signal should reach very far. " + 
            "2Well thank you for the help then... ",
            "2It seems as though it may rain soon, can you mind helping me to make something to mitigate that?%@2Maybe I can shelter in a home.@2Thank you.#1 " + 
            "2Thank you. ",
            "2(Cough)(Cough) Sorry I am a little sick, would you mind making me some medicine?%@2Thank you for asking though.@2Thank you.#1 " + 
            "2Thank you.(Cough) ",
            "2...&2Gatekeeper: It seems you may be worthy, you are permitted to go ahead. " + 
            "2Gatekeeper: Sorry but I cannot let you pass unless you prove yourself worthy to see Master Crane, maybe try going out and proving yourself to the others. ",
            // This is when we meet master Crane
            "0Swift: So you must be master Crane we heard about you so we could use a little help. We are kind of in the need of all terrain wheels. " + 
            "2..............." +
            "$a0Swift: Hey, pops! Do you hear me. " +
            "2..............." + 
            "0Swift: Whatever. Kid you deal with him I am gonna wait over here. " + 
            "1Finch: Um, hello Mr.Crane. " + 
            "$h2Crane: Hello young man, I can see you and your friend are stuck here for the time being. " + 
            "1Finch: What are you doing. " + 
            "$h2Crane: Meditation, why don't you join me. Sit down, my friend." + 
            "1Finch: ok&" + 
            "1Finch: Woah, we're are we? " + 
            "$h2Crane: Nowhere and everywhere. Now then what bothers you young man. " + 
            "1Finch: We'll you see... um... I have trouble inventing things. " + 
            "$h2Crane: Because you spend too much time overthinking am I correct? " + 
            "1Finch: Yes. " + 
            "$h2Crane: But you were able to make a few things before coming here am I correct. " + 
            "1Finch: Yes. " + 
            "$h2Crane: Let me tell you something young man. " + 
            "$h2Crane: We are spiritual builders and have built this place on this mountain inorder to further our connection with the building sprites. We may spend a lot of time meditating but eventually we have to act or else we would never have built this place. " + 
            "1Finch: But don't you worry that the ideas you have may not be good enough or that you might just only embarrass yourself. " + 
            "$h2Crane: Of course everyone has worries, but what is important is to not let them control you, but learn to control them and act anyway. Never let an opportunity become a lost opportunity that you regret later. " + 
            "$h2Crane: You need to learn this. The best way is through example! Let us begin#1 " +
            "$h2Crane: Thank you young man this back scratcher sure is nice when you have been meditating all day. Now then you have learned everything you need, so please take these two bus tickets. There is an exit behind me that will lead to the bus station nearby. " + 
            "0Swift: so you're finally done doing your extreme sleeping, let's go already*$h2Crane: Thank you for coming."
        });
        // Act Three
        npcText.add(new String[]{"2Secret: For this task you must follow me.&" +
            "1Finch: Ok what do I have to do?" +
            "2Secret: Don't die.&" +
            "$a1Finch: AHHH WE ARE GONNA DIE!" +
            "$a0Swift: I KNOW AND I AM NOT EVEN FAMOUS YET." +
            "3Owl: Calm down kid just invent something to help us survive. I have some parts you can borrow." +
            "$a0Swift: DO IT KID OR ELSE WE WILL BECOME SCRAMBLED EGGS!!#1 &" +
            "2Secret: Good job you have completed my task. Please continue" +
            "0Swift: I hope we never do that again.",
            "2Secret: For this task you must follow me.&" +
            "1Finch: What is all this about?" +
            "2Secret: Don't die." +
            "1Finch: AHHH WE ARE GONNA BE BLOWN UP!" +
            "$a0Swift: I KNOW AND I HAVE NOT EVEN KISSED A GIRL YET!" +
            "3Owl: Calm down kid just invent something to help us survive. I have some parts you can borrow." +
            "$a0Swift: DO IT KID OR ELSE WE WILL BECOME BURNT TOAST!#1 &" +
            "2Secret: Good job you have completed my task. Please continue" +
            "1Finch: I hope to never see another bomb again.",
            "2Secret: For this task you must follow me.&" +
            "1Finch: What am I supposed to do in this car?" +
            "2Secret: Don't die." +
            "1Finch: THE AIR IN THIS CAR IS DISAPPEARING!" +
            "$a0Swift: I KNOW AND I HAVEN'T BEAT MY GRAZE HIGH SCORE YET." +
            "3Owl: Calm down kid just invent something to help us survive. I have some parts you can borrow." +
            "$a0Swift: DO IT KID OR ELSE WE WILL BECOME A DEFLATED PUFFERFISH!#1 &" +
            "2Secret: Good job you have completed my task. Please continue." +
            "1Finch: I hope to never see another car again.",
            "2...&2Secret: It seems as though you have completed every task. I admit your entrance to villain-con.^" +
            "2Secret: I sense that you have not completed every task. I suggest you go back."
        });
        // Act Four
        npcText.add(new String[]{"2Ibis: Why hello there fellow Vallans. It is good to see you I am sure! " + 
            "1Finch: Yeah Ibis it is good to see you too, what did you make for the con? " + 
            "2Ibis: Well I used your wheels that I stole from your car to make a special spider vehicle that can climb up walls I sure am! " + 
            "1Finch: Wow that sounds cool! " + 
            "0Swift: Why do you always say, I sure am, it is getting kind of annoying and does not even make grammatical sense. " + 
            "2Ibis: I sure am it is none of your business. " + 
            "2Ibis: Anyway see you later I sure am. " + 
            "0Swift: liberal. ",
            "1Finch: Hey aren't you a spiritual builder?" +
            "2Yeah but don't tell Master Crane that I am a villain or else I will get in big trouble. " + 
            "0Swift: Of course. Well what did you build?" +
            "2I built a gas grenade that will put everyone to sleep once it is thrown. " + 
            "1Finch: Wow that sounds cool! " + 
            "0Swift: More like super lame and generic. " +
            "2Jee thanks. Maybe I will see you back at the mountain later. ",
            "0Swift: Whoah what is this weird bird doing here. " + 
            "2StiltBird: Hey I'm not weird. I'm an albatross, but just call me StiltBird. " + 
            "1Finch: Well StiltBird what did you make? " + 
            "2StiltBird: I'm honestly not that good at science stuff. I have two other friends WeirdBird who is a computer guy and RoundBird who plays the Tuba. We work as a team and are better together. I'm mostly just good at art and maybe some writing. " + 
            "0Swift: We didn't ask for your life story, just tell us what you made. " + 
            "2StiltBird: Well I didn't think you'd ever ask! I made a Baking Soda Volcano!  " + 
            "0Swift: How is that evil or villainous. " + 
            "2StiltBird: I had so much fun making it, it should be a crime. " + 
            "0Swift: Let's just go kid. That joke was too much." + 
            "2StiltBird: See you next Tuesday! ",
            "2Producer: Hello there young ones. It seems like this is your booth. What have you made?" +
            "1Finch: Uhh... Well you see.." +
            "0Swift: Finch! Remeber what everyone has taught you on this journey. It is time to show him everything you got." +
            "1Finch: Your right! Last one.#2" +
            "2Producer: What is this?" +
            "1Finch: It is a jar of slime that can be molded into any object you could ever need. It also becomes super sticky if you add water so it can be used as a trap. Furthermore it goes great with a classic Venezuelan Arepas De Queso dish." +
            "2Producer: Well ladies and gentle criminals I believe we have a winner. This year's Super Villain at Villain-Con is FINCH AND HIS TEACHER SWIFT.&"
        });
    }

    // Unlike npc text the current act does not matter. All dialogue is in chronological order.
    public void setMainDialogue() {
        mainDialogue.add(
            "2Dad: Oh you must be the person we called please come in. " + 
            "2Dad: Please wait here while I get my husband."
        );
        mainDialogue.add(
            "2Dad: So um, Miss. Swift was it? " + 
            "0Swift: Please please forget the formalities and just call me Swift. I understand that your son has been having trouble with his Villain studies? " + 
            "2Dad: As parents we try to not be involved and let him learn naturally, but he seems to be having some issues which is why we called you. " + 
            "0Swift: Of course of course, please be at ease whatever the problem is you have done the right thing by calling me." + 
            "0Swift: (What a couple of suckers, I can't believe they fell for my phony ad as a teacher, I don't even have a teaching degree.)" + 
            "0Swift: I also read over the information you gave me and it seems like I will have to take some drastic measures. " + 
            "$f2Dad: Is it really that bad? " + 
            "0Swift: From my expert perspective it is far worse than just  bad.  Normally this kind of help would cost a lot of money. " + 
            "$f2Dad: Please whatever it is we will pay. " + 
            "0Swift: Now don't worry about that. I am a very generous woman, I will even give you a discount…..if you switch to a different package. You see right now you and your family are currently on the bronze package which is a full one hour session however if you upgrade to the silver package I can do a full two hour session, but if you want even more help the GOLD package allows you to partake in a full three hour session or maybe the diamond package... " + 
            "2Dad: A four hour session? " + 
            "0Swift: No it is still three hours, but you get a complementary mug with a dog on it. " + 
            "2Dad: You know that mug sounds pretty nice. " + 
            "2Dad: Yeah we could make the neighbors so jealous! " + 
            "0Swift: I'll go see the boy now and let you consider your options. " + 
            "0Swift: Please look this over. " + 
            "2Dad: Yes thank you so much. He is in the room at the end of the hallway. "
        );
        mainDialogue.add(
            "0Swift: Hello you must be Finch. Your parents called me in to help you with your villain studies. Seems like you are already reading. I understand that when trying to become the next big super villain it may seem intimidating. As you can see I am great myself but I was not always like this. I came here to teach you how to be as good as me. Right now you just need to get into a new groove, what do you say. " + 
            "1Finch: I can't believe they did that." + 
            "0Swift: Pay attention here kid. " + 
            "1Finch: Who even are you? " + 
            "0Swift: You can call me Swift and I am here to help you become a better villain. As our first task I want you to show me your latest invention. " + 
            "1Finch: Um, Swift I don't know if that is a good idea? " + 
            "0Swift: Look kid, what you think doesn't matter because you're not the genius here, just let me see them. " + 
            "1Finch: ok.&" + 
            "0Swift: Wow...wow you built a shelf great...great, but where are the villainous inventions. " + 
            "1Finch: No I didn't build the shelf we got it from this villains-only swedish furniture store called Ike... " + 
            "0Swift: Look kid just show me something you actually made. " + 
            "1Finch: Well technically I have never made anything. " + 
            "0Swift: I can see you have all these technical books so you must know how to make stuff. " + 
            "1Finch: Well usually what happens is that when I try to make stuff I kind of get lost in my head and spend too much time preparing for it and then eventually I lose the opportunity to make it. " + 
            "0Swift: What about the local Villain-Con Jr. aren't all you kids required to enter. " + 
            "1Finch: I never handed in the permission slip because I knew that I would never be able to actually make something by the time it ended. " + 
            "0Swift: Look, just make something. I will even help you free of charge. " + 
            "1Finch: But I... " + 
            "0Swift: NOW!#0 " + 
            "0Swift: So you made a button? Not too bad I have seen worse, but we can work from here. " + 
            "1Finch: No it is an invisibility shield. You just press the button and you go invisible! " + 
            "0Swift: Listen kid I know that those villain tv shows have all these wacky ideas but we need to think in the realm of reality." +
            "1Finch: No it's real. Here I'll show you.&" + 
            "0Swift: (HOW IS THAT EVEN POSSIBLE?! No wait. Think swift, think! I can use this kid’s knowledge to my advantage.) " + 
            "0Swift: Hey kid do you know what patents are… Wait no. Do not answer that. How old did you say you were? " + 
            "1Finch: I am twelve years old. " + 
            "0Swift: So this is your last year to enter Villain-con JR. You may have lost every other opportunity but this year is your chance. " + 
            "1Finch: But what if I fail or mess up and embarrass myself. " + 
            "0Swift: Think of the prize money and how famous you...no I could be. I can see it now. Super genius teacher finds a student who creates an amazing invention. " + 
            "1Finch: But you didn't teach me anything. " + 
            "0Swift: Look kid it's tomato potato, you miss all of the shots you don't take and I hate hearing the phrase, you lost. " + 
            "1Finch: I'm still not sure. Maybe I should just keep studying " + 
            "0Swift: No! You are not gonna get anywhere with those books. We gotta get to villain-con quick! It is only a few days away. Me and you will take your parents car and get going now! " + 
            "1Finch: Don't you already have a car? " + 
            "0Swift: Lesson one, a villain never uses anything he or she can't steal. Also let's sneak out this window so your parents dont know im kidnappin-…I mean taking you to Villain-Con."
        );
        mainDialogue.add("0Swift: Well that stinks the car stopped and we can't continue. The battery must be dead. We are gonna have to find a replacement." +
        "1Finch: What if we are late to the Villain-con! That would be a true diseaster." +
        "0Swift: Listen kid. It's alright that it didn't go to plan. There are always gonna be bumps in the road." +
        "1Finch: Alright. I just hope we can get there in time before I make a fool of myself." +
        "0Swift: We will be fine. I can see a little town up ahead. Maybe there is a mechanic that can help fix our car."
        );
        mainDialogue.add("0Swift: You got to be kidding me." +
            "$a0Swift: MY TIRES WERE STOLEN!! " + 
            "1Finch: Technically it was my Dads’ tires. " +
            "0Swift: What are we gonna do! Someone must have stole them." +
            "1Finch: From the looks of this town it could be possible." + 
            "0Swift: Let's show this thief what happens when you mess with a genius villain like me. Maybe it was that broken gate back near the mechanic!&"
        );
        mainDialogue.add("0Swift: AHA it seems as though you have lost you little rascal. " + 
            "2Ibis: Hello there fellow vallans the names Ibis and I was just using your tires for a ol project I gotta finish for Vallan-can I sure am. " + 
            "0Swift: How do you know we are villains. " + 
            "2Ibis: You don't look like the type to own an electric car so I guess it's stolen. " + 
            "1Finch: You're also entering Villain-con, Ibis? " + 
            "2Ibis: Why yes sir I sure am, I love inventing and I am sure I am gonna win Vallancan. " + 
            "0Swift: Look kid, just hand over the tires or else I will call the Villain-Police. " + 
            "2Ibis: Sorry Ma'am no can do, I sure am, but I can help you build something else to get outta here. " + 
            "1Finch: But all these parts are so old and rusty I think we need to build using only professional parts. " + 
            "2Ibis: I have been inventing ever since I was a small girl I sure am, I don't have a lot o dough to spend so I just use whatever junk I find lying around. If I gave up on building just because of using a few old broken parts then I would have lost the opportunity to be where I am now entering vallan-can. It's not the tools but how you use them, fellow vallan. " + 
            "1Finch: Ok I think we can build something.#1 " + 
            "2Ibis: This bike looks great I sure am. " + 
            "0Swift: If you kids are done playing I think it is time to go. " + 
            "1Finch: Thanks Ibis I could not have done it without you! " + 
            "2Ibis: No problem fellow Vallan I guess I will see you at villain-con.^"
        );
        mainDialogue.add("0Swift: The bike we got from Ibis is clearly not suited for the these mountains." +
            "1Finch: Does that mean we are gonna have to walk out from here?" +
            "0Swift: We will be fine. I can see something at the top. Maybe that will guide us."
        );
        mainDialogue.add("0Swift: The bus trip shouldn't last too long.^");
        mainDialogue.add("0Swift: Here we are, Villain-Con should be somewhere around here." +
            "1Finch: I wonder what Villian-con will look like.&" +
            "0Swift: Probably something evil and villainous. This is a huge city anyway it seems like it goes on forever." +
            "3???: Hello I heard you two were talking about Villain-Con." +
            "1Finch: Yeah I am gonna enter." + 
            "3???: (This looks like my ticket in. But I can not let my guard down.)" +
            "3Owl: My name is agent...I mean Mr. Owl I am a Villain as well and I have misplaced my ticket could I accompany you two for the time being." + 
            "0Swift: You kind of look like a spy from the Hero Association, you know with the suit and all." + 
            "3Owl: Nonsense I just like wearing suits." + 
            "0Swift: And your ear piece?" + 
            "3Owl: What can I say I love listening to music." + 
            "0Swift: And your sunglasses?" + 
            "3Owl: Gotta protect my eyes from solar radiation somehow." + 
            "1Finch: I think it will be fine." +
            "3Owl: (All According to plan! I easily fooled them into thinking I am a villain!)" + 
            "0Swift: Ok but I got my eye on you. Don't forget what I told you Finch, in order to enter Villain-Con you need to complete tasks by the three secret members in order to prove yourself.");
        mainDialogue.add("1Finch: You know Owl during all those death traps you never freaked out or anything. " + 
            "3Owl: I know. " + 
            "1Finch: How can you stay calm and act during all that pressure, you're totally fearless. " + 
            "3Owl: Listen here buddy, only an idiot is completely fearless. Of course I feel fear for myself and others if I am unable to do the right thing and help them." + 
            "3Owl: A lot of times I have to make split second decisions that could mean life or death. I would rather choose to do something even if I am not sure if what I am about to do will get me out of trouble or not. If I choose to freeze up then I would have lost my life countless times by now. Don't let any opportunity stop you from acting or else they will become a lost opportunity. " + 
            "1Finch: Thanks Owl." + 
            "3Owl: No problem buddy. Now let's head to Villain-Con."
        );
        mainDialogue.add("3Owl: (In ear piece)The infiltration was a success. I am going to see what information they currently have." +
            "3Owl: Well it was nice having you, but I must be on my way." +
            "1Finch: Thanks again Owl." +
            "0Swift: Smell ya later&" +
            "0Swift: This place is huge. You would never know it from the entrance. Let’s look around."
        );
        mainDialogue.add("1Finch: So that's how I won villain-con and became a super villian, pretty cool right? " +
        "1Finch: I think the lesson we all learned here is that even if you have doubts or are afraid it is always ok to take a chance even when the outcome may not be so clear. Because I never wanna have a lost opportunity every again." +
        "1Finch: Time to do something villainous!"
        );
        mainDialogue.add("0Swift: TIME TRAVEL.^");
    }    
}