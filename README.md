# HeroGuessr 

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
HeroGuessr is an application that allows users to brush up on their superhero knowledge. If they wish, users can also put their matchup knowledge to the test and see how they fare against others on the leaderboards.

### App Evaluation
- **Category:** Social Networking / Informational / Trivia
- **Mobile:** Once you click the VS option on the bottom of the screen, it will randomize two superheroes and allow the user to guess which one would win. The user will be able to use their camera to update their profile picture which makes it visually appealing while viewing statistics. The leaderboards would show their statistics and have a filter based off their location (to see nearby top users).
- **Story:** Users look up various superheroes and compare stats with other heroes. The hero with better stats wins and the user's win/loss record is updated. There is a leaderboard showing the top users on the app. 
- **Market:** The market for this application would be 7+ year olds who are fascinated by the superhero scene. It will allow them to spend their free time battling randomized superhero's to guess their way to the top of the leaderboards. Statistics show that a large number of children are hooked onto superhero shows, comics, and movies early in their childhood and stay intrigued majority of their life, which allows this application to be readily available for them to enjoy throughout their day. 
- **Habit:** This application could really be addictive if done correctly. For example, Tiktok is an application that people spend hours daily on to see content from other people, and this application would allow the younger audience to learn about their interests in superheroes and spend countless hours working on their knowledge.
- **Scope:** This application is unique as it allows users to continuously play the guessing game without having to deal with other users (opponents). It individually allows randomized superheroes using the Superhero API to "compete". It isn't  technically challenging if you know the basics of app development, utilization of an API and a database, and providing visually appealing pages/leaderboards to the audience.

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**
 * As a user, I should be able to log in, sign up, and log out.
 * As a user, I should be able to search for a hero from the Superhero API database.
 * As a user, I should be able to view various info about the heroes I search for.
 * As a user, I should be able to play a guessing game based on random matchups.
 * As a user, I should be able to compare heroes' power stats and see who is likely to win in a given matchup.
 * As a user, I should be able to view my game statistics (% guessed correctly, hero I'm most likely to underrate, etc.)
 * As a user, I should be able to change my profile picture.
 * As a user, I should be able to view a leaderboard (% guessed correctly, >50? total guesses)

**Optional Nice-to-have Stories**
* As a user, I should be able to discuss with others in a forum.

### 2. Screen Archetypes

* Login
    * As a user, I should be able to log in, sign up, or log out.
* Hero Information Screen
    * As a user, I should be able to search for a hero from the Superhero API database.
     * As a user, I should be able to view various info about the heroes I search for.
* Matchup Screen
   *  As a user, I should be able to guess who is likely to win in a given matchup.
   *  As a user, I should be able to see who will win based on power stats.
* Profile Screen
    * As a user, I should be able to view my game statistics (% guessed correctly, hero I'm most likely to underrate, etc.)
    * As a user, I should be able to change profile picture.
    * As a user, I should be able to log out.
* Leaderboard Screen
    * As a user, I should be able to view a leaderboard (% guessed correctly, >50? total guesses)


### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Hero Information
* Matchup
* Profile
* Leaderboard

**Flow Navigation** (Screen to Screen)

* Log in or sign up -> Matchup screen
* Matchup screen -> Blind matchup guessing game screen
* Profile -> User information screen
* Leaderboard -> Leaderboard screen
* Search screen -> Displays hero information
* Log out -> Log in or sign up page

## Wireframes

<img src="https://i.imgur.com/Sqeafu3.jpg" width=600>

## Schema 
[This section will be completed in Unit 9]
### Models
[Add table of models]
### Networking
- [Add list of network requests by screen ]
- [Create basic snippets for each Parse network request]
- [OPTIONAL: List endpoints if using existing API such as Yelp]
