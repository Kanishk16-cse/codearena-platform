import React from 'react'
import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Join from './pages/Join.jsx'
import Challenge from './pages/Challenge.jsx'
export default function App(){return(<BrowserRouter><Routes><Route path="/" element={<Join/>}/><Route path="/challenge/:id/:username" element={<Challenge/>}/></Routes></BrowserRouter>)}