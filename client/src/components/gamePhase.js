import './gamePhase.scss';
import React from 'react';
import { GameForm } from './gameForm';
import { RivalForm } from './rivalForm';
import { GameResult } from './gameResult';
import paper from '../resources/img/paper.jpg';
import rock from '../resources/img/rock.jpg';
import scissor from '../resources/img/scissor.jpg';

const buttons = [{
    value: 'P',
    img: paper,
    alt: 'paper'
},
{
    value: 'R',
    img: rock,
    alt: 'rock'
},
{
    value: 'S',
    img: scissor,
    alt: 'scissor'
}];

export const GamePhase = ({ makeTurn, playersResult }) => {
    return <div className='game-phase'>
        {playersResult.outcome ? <GameResult outcome={playersResult.outcome} /> : null}
        <RivalForm buttons={buttons} skynetChoice={playersResult.skynetChoice} />
        <GameForm buttons={buttons} makeTurn={makeTurn} playerChoice={playersResult.playerChoice} />
    </div>
}