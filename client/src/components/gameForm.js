import './gameForm.scss';
import React from 'react';
import { Button } from './button';

export const GameForm = ({ makeTurn, playerChoice, buttons }) => {
    return <div className='button-group player-gropup'>
        {buttons.map((it, index) => {
            const classes = playerChoice === it.value ? 'button_active' : '';
            const classesRound = playerChoice ? 'button_disable' : '';
            return <Button className={`button ${classes} ${classesRound} `} onClick={() => makeTurn({ choice: it.value })} key={index}>
                <img src={it.img} alt={it.alt} />
            </Button>
        })}
    </div>
};
