import './gameFinalResult.scss';
import React from 'react';

export const GameFinalResult = ({ stats }) => {
    const { wins, loses, draws } = stats;

    return <div className='game-final-result'>
        <p>Wins: {wins}</p>
        <p>Loses: {loses}</p>
        <p>Draws: {draws}</p>
    </div>
};
