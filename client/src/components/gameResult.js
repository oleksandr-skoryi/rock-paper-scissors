import './gameResult.scss';
import React from 'react';

export const GameResult = ({ outcome }) => {
    let message = '';
    if (outcome === 'WIN') {
        message = 'You win!';
    }
    if (outcome === 'LOSE') {
        message = 'You lose!';
    }
    if (outcome === 'DRAW') {
        message = 'Round draw';
    }

    return <div className='game-result'>
        <h2>{message}</h2>
    </div>
}